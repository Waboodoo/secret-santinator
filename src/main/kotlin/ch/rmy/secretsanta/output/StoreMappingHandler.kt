package ch.rmy.secretsanta.output

import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.TimeProvider
import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.people.Person
import ch.rmy.secretsanta.writeJson
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StoreMappingHandler(
    private val mappingsDirectory: File,
    private val timeProvider: TimeProvider = TimeProvider.default,
) : MappingHandler {

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        prettyPrint = true
        prettyPrintIndent = "  "
    }

    override fun handle(matches: Map<Person, Person>) {
        val now = timeProvider.now()

        mappingsDirectory.mkdirs()
        val outputFile = File(mappingsDirectory, createFileName(now))

        val mapping = Mapping(
            matches = matches
                .map { (gifter, giftee) ->
                    gifter.id to giftee.id
                }
                .toMap(),
            year = now.year,
        )

        json.writeJson(outputFile, mapping)
    }

    private fun createFileName(now: LocalDate) =
        DateTimeFormatter.ISO_DATE.format(now) + ".json"
}
