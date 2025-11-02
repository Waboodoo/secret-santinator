package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person
import ch.rmy.secretsanta.scoring.Scorer
import kotlin.random.Random

class ScoredMatchMaker(
    private val delegate: MatchMaker,
    private val scorer: Scorer,
    private val random: Random = Random,
    private val iterations: Int = 10,
) : MatchMaker {

    fun <T> pickByWeight(candidates: List<T>, weights: List<Int>): T {
        val min = weights.min()
        val weightsPositive = weights.map { s -> s - min }

        val cdf = weightsPositive
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
        println("Choice: $choice")

        return candidates[choice]
    }

    override fun run(people: Set<Person>): Set<Match> {
        val candidates = List(iterations) { delegate.run(people) }

        val scores = candidates.map { candidate ->
            scorer.score(candidate)
        }
        println("Scores: $scores")

        return pickByWeight(candidates, scores)

    }
}
