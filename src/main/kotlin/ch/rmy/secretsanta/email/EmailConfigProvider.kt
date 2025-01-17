package ch.rmy.secretsanta.email

import ch.rmy.secretsanta.ConfigFiles
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import java.io.File

class EmailConfigProvider(
    configFile: File = File(ConfigFiles.EMAIL),
) {
    private val config: MailerConfig = configFile.inputStream().use { stream ->
        Yaml.default.decodeFromStream(stream)
    }

    val server: ServerConfig
        get() = config.server

    val message: MessageConfig
        get() = config.message
}
