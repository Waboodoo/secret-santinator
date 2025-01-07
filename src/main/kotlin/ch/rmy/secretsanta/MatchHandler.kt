package ch.rmy.secretsanta

interface MatchHandler {
    fun handle(matches: Set<Match>)
}