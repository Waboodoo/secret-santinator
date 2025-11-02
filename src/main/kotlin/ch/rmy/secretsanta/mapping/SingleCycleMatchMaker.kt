package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId
import kotlin.random.Random

/**
 * Generates a single cycle
 */
class SingleCycleMatchMaker(
    private val random: Random = Random,
) : MatchMaker {
    override fun run(people: Set<PersonId>): Set<Match> {
        val gifters = people.shuffled(random)
        val giftees = gifters.drop(1).plus(gifters.first())
        return gifters.zip(giftees, ::Match)
            .sortedBy { it.gifter }
            .toSet()
    }
}
