package ch.rmy.secretsanta.output

import ch.rmy.secretsanta.people.PersonId
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

class PastMappingReader(
    private val mappingsDirectory: File,
) {
    @OptIn(ExperimentalSerializationApi::class)
    fun read(): Map<PersonId, Set<PersonId>> = buildMap<PersonId, MutableSet<PersonId>> {
        (mappingsDirectory.listFiles { _, name -> name.endsWith(".json") } ?: emptyArray<File>())
            .forEach { mappingFile ->
                mappingFile.inputStream().use { inputStream ->
                    Json.decodeFromStream<Map<PersonId, PersonId>>(inputStream)
                }
                    .forEach { (gifterId, gifteeId) ->
                        getOrPut(gifterId) { mutableSetOf() }.add(gifteeId)
                    }
            }
    }
}
