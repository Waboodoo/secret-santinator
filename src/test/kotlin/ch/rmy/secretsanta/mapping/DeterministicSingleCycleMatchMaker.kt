package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId

class DeterministicSingleCycleMatchMaker : MatchMaker {
    override fun run(people: Set<PersonId>): Set<Match> {
        val giftees = people.drop(1).plus(people.first())
        return people.zip(giftees, ::Match)
            .sortedBy { it.gifter }
            .toSet()
    }
}
