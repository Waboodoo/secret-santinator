package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person
import ch.rmy.secretsanta.people.PersonId

class VarietyMatchMaker(
    private val delegate: MatchMaker,
    private val discouragedMappings: Map<PersonId, Set<PersonId>>,
    private val iterations: Int = 20,
) : MatchMaker {
    override fun run(people: Set<Person>): Set<Match> {
        repeat(iterations) {
            val mapping = delegate.run(people)
            if (mapping.none { match -> discouragedMappings[match.gifter.id]?.contains(match.giftee.id) == true }) {
                return mapping
            }
        }
        return delegate.run(people)
    }
}
