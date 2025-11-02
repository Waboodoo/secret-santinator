package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.PersonId
import ch.rmy.secretsanta.scoring.Scorer
import kotlin.random.Random
import kotlin.math.pow

class ScoredMatchMaker(
    private val delegate: MatchMaker,
    private val scorer: Scorer,
    private val random: Random = Random,
    private val iterations: Int = 10,
) : MatchMaker {

    fun <T> pickByWeight(candidates: List<T>, weights: List<Int>): T {
        val weightsPositive = weights.map { s -> Math.max(s, 0)  }

        val weightsExponential = weightsPositive.map { s -> if (s > 0) Math.E.pow(s).toInt() else s }

        val cdf = weightsExponential
            .scan(0) { sum, score ->
                sum + score
            }
            .drop(1)

        val cdfmax = cdf.max()

        if (cdfmax == 0) {
            return candidates.random(random)
        }

        val pick = random.nextFloat() * cdfmax
        val choice = cdf.indexOfFirst { cdfscore -> cdfscore > pick }
        println("Choice: $choice (with score ${weightsPositive[choice]})")

        return candidates[choice]
    }

    override fun run(people: Set<PersonId>): Set<Match> {
        val candidates = List(iterations) { delegate.run(people) }

        val scores = candidates.map { candidate ->
            scorer.score(candidate)
        }

        return pickByWeight(candidates, scores)

    }
}
