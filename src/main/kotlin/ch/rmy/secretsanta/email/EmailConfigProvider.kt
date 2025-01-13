package ch.rmy.secretsanta.email

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import java.io.File

class EmailConfigProvider(
    configFile: File = File("email_config.yml"),
) {
    private val config: MailerConfig = configFile.inputStream().use { stream ->
        Yaml.default.decodeFromStream(stream)
    }

    val server: ServerConfig
        get() = config.server

    val message: MessageConfig
        get() = config.message
}
