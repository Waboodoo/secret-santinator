package ch.rmy.secretsanta.email

import kotlinx.serialization.Serializable

@Serializable
data class EmailMessageConfig(
    val senderAddress: String,
    val senderName: String,
    val subject: String,
    val htmlBody: String,
)
