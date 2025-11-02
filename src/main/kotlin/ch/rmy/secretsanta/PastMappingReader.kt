package ch.rmy.secretsanta

import ch.rmy.secretsanta.mapping.Mapping
import java.io.File

class PastMappingReader(
    private val mappingsDirectory: File,
) {
    fun read(): Set<Mapping> =
        (mappingsDirectory.listFiles { _, name -> name.endsWith(".json") } ?: emptyArray<File>())
            .map { mappingFile ->
                mappingFile.readJson<Mapping>()
            }
            .toSet()
}
