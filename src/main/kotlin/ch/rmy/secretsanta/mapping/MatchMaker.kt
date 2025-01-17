package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person

interface MatchMaker {
    fun run(people: Set<Person>): Set<Match>
}
