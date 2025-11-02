package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId
import kotlinx.serialization.Serializable

@Serializable
data class Mapping(
    val matches: Map<PersonId, PersonId>,
    val year: Int? = null,
    val scoreMultiplier: Float? = null,
)