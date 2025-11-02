package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match

interface Scorer {
    fun score(matches: Set<Match>): Int
}