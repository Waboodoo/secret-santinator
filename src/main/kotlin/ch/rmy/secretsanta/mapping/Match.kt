package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person

data class Match(
    val gifter: Person,
    val giftee: Person,
)
