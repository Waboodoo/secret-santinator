package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId

data class Match(
    val gifter: PersonId,
    val giftee: PersonId,
) {
    override fun toString() =
        "[$gifter -> $giftee]"
}
