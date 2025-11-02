package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId

fun interface MatchMaker {
    fun run(people: Set<PersonId>): Set<Match>
}
