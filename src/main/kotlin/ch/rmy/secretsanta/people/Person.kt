package ch.rmy.secretsanta.people

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val name: String,
    val email: String,
    val id: PersonId = generateId(name, email),
) {
    init {
        require(name.isNotEmpty())
        require(email.isNotEmpty())
    }
}

private fun generateId(name: String, email: String): PersonId =
    arrayOf(
        name.first(),
        name.last(),
        String.format("%04d", (name + email).chars().sum().mod(10000)),
    )
        .joinToString(separator = "")
