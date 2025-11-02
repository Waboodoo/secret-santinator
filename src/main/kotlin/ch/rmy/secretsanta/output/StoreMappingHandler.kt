package ch.rmy.secretsanta.output

import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.mapping.Match
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StoreMappingHandler(
    private val mappingsDirectory: File,
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
            year = LocalDate.now().year,
        )

        outputFile.outputStream().use { outStream ->
            json.encodeToStream(mapping, outStream)
        }
    }

    private fun createFileName(): String {
        val timestamp = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())
        return "${timestamp.replace(":", "_")}.json"
    }
}
