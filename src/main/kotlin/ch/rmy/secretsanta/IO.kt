package ch.rmy.secretsanta

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import kotlin.io.inputStream
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> File.readJson(): T =
    inputStream().use { inputStream ->
        Json.decodeFromStream<T>(inputStream)
    }

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> Json.writeJson(file: File, data: T) {
    file.outputStream().use { outStream ->
        encodeToStream(data, outStream)
    }
}

inline fun <reified T> File.readYaml(): T =
    inputStream().use { stream ->
        Yaml.default.decodeFromStream<T>(stream)
    }
