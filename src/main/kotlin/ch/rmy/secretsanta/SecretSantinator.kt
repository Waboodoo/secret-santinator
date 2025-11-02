package ch.rmy.secretsanta

import ch.rmy.secretsanta.mapping.MatchMaker
import ch.rmy.secretsanta.people.PeopleProvider

class SecretSantinator(
    private val peopleProvider: PeopleProvider,
    private val matchMaker: MatchMaker,
    private val mappingHandlers: Collection<MappingHandler>,
) {
    fun run() {
        val people = peopleProvider.getPeople()
        require(people.size >= 3) { "At least 3 people are required" }

        val personIds = people.map { it.id }.toSet()
        val matches = matchMaker.run(personIds)

        val peopleMap = people.associateBy { it.id }
        val mapping = matches.associate { peopleMap[it.gifter]!! to peopleMap[it.giftee]!! }
        mappingHandlers.forEach { it.handle(mapping) }
    }
}
