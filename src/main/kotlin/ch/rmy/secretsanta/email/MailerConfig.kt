package ch.rmy.secretsanta.email

import kotlinx.serialization.Serializable

@Serializable
data class MailerConfig(
    val server: ServerConfig,
    val message: MessageConfig,
)
