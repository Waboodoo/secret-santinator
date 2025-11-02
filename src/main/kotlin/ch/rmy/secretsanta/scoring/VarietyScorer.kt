package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.mapping.Mapping

class VarietyScorer(
    private val mappings: Set<Mapping>,
) : Scorer {
    override fun score(matches: Set<Match>): Int {
        TODO("Not yet implemented")
    }
}