package ch.rmy.secretsanta

object PrintMatchHandler : MatchHandler {
    override fun handle(matches: Set<Match>) {
        matches.forEach { (gifter, giftee) ->
            println("${gifter.name} \t => \t ${giftee.name}")
        }
    }
}