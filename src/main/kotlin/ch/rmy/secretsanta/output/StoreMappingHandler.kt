package ch.rmy.secretsanta.output

import ch.rmy.secretsanta.ConfigFiles
import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.mapping.Match
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StoreMappingHandler(
    private val mappingsDirectory: File = File(ConfigFiles.MAPPINGS_DIR),
) : MappingHandler {

    private val json = Json {
        prettyPrint = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun handle(mapping: Set<Match>) {
        val outputFile = File(mappingsDirectory, createFileName())
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
