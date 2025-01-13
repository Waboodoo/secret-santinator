package ch.rmy.secretsanta.email

import kotlinx.serialization.Serializable

@Serializable
data class MessageConfig(
    val senderAddress: String,
    val senderName: String,
    val subject: String,
    val htmlBody: String,
)
