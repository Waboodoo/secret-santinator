package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person
import ch.rmy.secretsanta.people.PersonId

/**
 * Generates a valid mapping, while trying to avoid as many discouraged matches as possible.
 */
class VarietyMatchMaker(
    private val delegate: MatchMaker,
    private val discouragedMappings: Map<PersonId, Set<PersonId>>,
    private val iterations: Int = 20,
) : MatchMaker {
    init {
        require(iterations >= 1)
    }

    override fun run(people: Set<Person>): Set<Match> {
        var bestMappingSoFar: Set<Match>? = null
        var bestProblemCountSoFar: Int = Int.MAX_VALUE

        repeat(iterations) {
            val mapping = delegate.run(people)

            val problemCount = mapping.count { match ->
                discouragedMappings[match.gifter.id]?.contains(match.giftee.id) == true
            }

            if (problemCount == 0) {
                return mapping
            }

            if (problemCount < bestProblemCountSoFar) {
                bestProblemCountSoFar = problemCount
                bestMappingSoFar = mapping
            }
        }

        return bestMappingSoFar!!
    }
}
