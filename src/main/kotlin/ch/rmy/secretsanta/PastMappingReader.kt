package ch.rmy.secretsanta

import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.people.PersonId
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

class PastMappingReader(
    private val mappingsDirectory: File,
) {
    @OptIn(ExperimentalSerializationApi::class)
    fun read(): Set<Mapping> =
        (mappingsDirectory.listFiles { _, name -> name.endsWith(".json") } ?: emptyArray<File>())
            .map { mappingFile ->
                mappingFile.inputStream().use { inputStream ->
                    Json.decodeFromStream<Mapping>(inputStream)
                }
            }
            .toSet()
}
