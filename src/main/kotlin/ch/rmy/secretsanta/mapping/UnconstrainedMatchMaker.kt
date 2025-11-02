package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId
import kotlin.random.Random

/**
 * Generates an arbitrary valid mapping
 */
class UnconstrainedMatchMaker(
    private val random: Random = Random,
    private val subdivisionGenerator: SubdivisionGenerator = SubdivisionGenerator(),
    private val delegate: MatchMaker,
) : MatchMaker {
    override fun run(people: Set<PersonId>): Set<Match> {
        val subdivisions = subdivisionGenerator.generate(n = people.size)
        val subdivision = subdivisions.random(random)
        val peopleIterator = people
            .shuffled(random)
            .iterator()

        return subdivision
            .map { n ->
                buildSet {
                    repeat(n) {
                        add(peopleIterator.next())
                    }
                }
            }
            .flatMap { peopleSubset ->
                delegate.run(peopleSubset)
            }
            .sortedBy { it.gifter }
            .toSet()
    }
}
