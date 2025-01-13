package ch.rmy.secretsanta

import ch.rmy.secretsanta.people.Person

data class Match(
    val gifter: Person,
    val giftee: Person,
)
