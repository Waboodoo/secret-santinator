package ch.rmy.secretsanta.email

import kotlinx.serialization.Serializable

@Serializable
data class ServerConfig(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
)