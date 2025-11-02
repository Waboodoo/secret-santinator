package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match
import kotlin.math.roundToInt

class CycleLengthScorer(
    private val maxScore: Int = 10,

): Scorer {
    override fun score(matches: Set<Match>): Int {
        val length = matches.size
        var shortestCycle = Int.MAX_VALUE

        val graph = matches.associateBy { it.gifter }

        matches.forEach{ match ->
            var cl = 0
            var next = graph[match.gifter]!!

            do {
                next = graph[next.giftee]!!
                cl++
            } while (next != match)

            if(cl < shortestCycle) {
                shortestCycle = cl
            }
        }

        return (((shortestCycle.toFloat() - 2) / (length.toFloat() - 2)) * this.maxScore).roundToInt()
    }
}