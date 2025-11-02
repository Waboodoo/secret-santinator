package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person
import kotlin.random.Random

/**
 * Generates a single cycle
 */
class SingleCycleMatchMaker(
    private val random: Random = Random,
) : MatchMaker {
    override fun run(people: Set<Person>): Set<Match> {
        val gifters = people.shuffled(random)
        val giftees = gifters.drop(1).plus(gifters.first())
        return gifters.zip(giftees, ::Match)
            .sortedBy { it.gifter.id }
            .toSet()
    }
}
