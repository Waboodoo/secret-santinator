package ch.rmy.secretsanta

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import java.io.File
import kotlin.io.inputStream

inline fun <reified T> File.readYaml(): T =
    inputStream().use { stream ->
        Yaml.default.decodeFromStream<T>(stream)
    }
