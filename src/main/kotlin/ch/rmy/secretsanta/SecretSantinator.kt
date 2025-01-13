package ch.rmy.secretsanta

import ch.rmy.secretsanta.people.PeopleProvider

class SecretSantinator(
    private val peopleProvider: PeopleProvider,
    private val matchMaker: MatchMaker,
    private val matchHandler: MatchHandler,
) {
    fun run() {
        val people = peopleProvider.getPeople()
        val matches = matchMaker.run(people)
        matchHandler.handle(matches)
    }
}
