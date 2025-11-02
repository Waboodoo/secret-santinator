package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match

class CompositeScorer(
    private val scorers: List<Scorer>
) : Scorer {

    override fun score(matches: Set<Match>): Int =
        scorers.fold(0) { score, scorer ->
            score + scorer.score(matches)
        }
}