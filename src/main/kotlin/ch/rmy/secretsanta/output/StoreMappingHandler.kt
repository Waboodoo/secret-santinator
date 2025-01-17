package ch.rmy.secretsanta.output

import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.Match
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StoreMappingHandler(
    private val outputDirectory: File = File("mappings"),
) : MappingHandler {

    private val json = Json {
        prettyPrint = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun handle(mapping: Set<Match>) {
        val outputFile = File(outputDirectory, createFileName())
        outputFile.outputStream().use { outStream ->
            json
                .encodeToStream(
                    mapping.associate { (gifter, giftee) ->
                        gifter.id to giftee.id
                    },
                    outStream,
                )
        }
    }

    private fun createFileName(): String =
        DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())
            .replace(":", "_")
            .plus(".json")
}
