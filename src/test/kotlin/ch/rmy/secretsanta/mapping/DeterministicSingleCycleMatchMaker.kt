package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person

class DeterministicSingleCycleMatchMaker : MatchMaker {
    override fun run(people: Set<Person>): Set<Match> {
        val giftees = people.drop(1).plus(people.first())
        return people.zip(giftees, ::Match)
            .sortedBy { it.gifter.id }
            .toSet()
    }
}
