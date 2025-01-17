package ch.rmy.secretsanta

import ch.rmy.secretsanta.mapping.Match

interface MappingHandler {
    fun handle(mapping: Set<Match>)
}
