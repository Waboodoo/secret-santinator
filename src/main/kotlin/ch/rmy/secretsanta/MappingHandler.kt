package ch.rmy.secretsanta

import ch.rmy.secretsanta.mapping.Match

interface MappingHandler {
    fun handle(matches: Set<Match>)
}
