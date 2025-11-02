package ch.rmy.secretsanta.output

import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.TimeProvider
import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.mapping.Match
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.time.format.DateTimeFormatter

class StoreMappingHandler(
    private val mappingsDirectory: File,
    private val timeProvider: TimeProvider = TimeProvider.default,
) : MappingHandler {

    private val json = Json {
        prettyPrint = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun handle(matches: Set<Match>) {
        mappingsDirectory.mkdirs()
        val outputFile = File(mappingsDirectory, createFileName())

        val mapping = Mapping(
            matches = matches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
            year = timeProvider.now().year,
        )

        outputFile.outputStream().use { outStream ->
            json.encodeToStream(mapping, outStream)
        }
    }

    private fun createFileName(): String {
        val timestamp = DateTimeFormatter.ISO_DATE.format(timeProvider.now())
        return "${timestamp.replace(":", "_")}.json"
    }
}
