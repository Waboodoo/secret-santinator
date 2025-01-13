package ch.rmy.secretsanta

import ch.rmy.secretsanta.people.Person
import kotlin.random.Random

class MatchMaker(
    private val random: Random = Random,
) {
    fun run(
        people: Set<Person>,
    ): Set<Match> {
        require(people.size >= 2) { "At least 2 people are required" }

        val gifters = people.shuffled(random)
        val giftees = gifters.drop(1).plus(gifters.first())
        return gifters.zip(giftees, ::Match).toSet()
    }
}
