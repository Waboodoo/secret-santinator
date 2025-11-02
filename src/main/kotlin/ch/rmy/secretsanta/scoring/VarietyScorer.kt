package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.TimeProvider
import ch.rmy.secretsanta.people.PersonId
import kotlin.math.pow
import kotlin.math.roundToInt

class VarietyScorer(
    private val mappings: Set<Mapping>,
    private val timeProvider: TimeProvider = TimeProvider.default,
    private val maxScore: Int = 10,
) : Scorer {
    override fun score(matches: Set<Match>): Int {

        return mappings.fold(maxScore) { score, pastMapping ->
            Math.min(score, maxScore - (calculateFactorFor(pastMapping) * scoreVsOne(matches, pastMapping.matches)).roundToInt())
        }
    }

    private fun calculateFactorFor(pastMapping: Mapping): Float {
        if (pastMapping.scoreMultiplier != null) {
            return pastMapping.scoreMultiplier.toFloat()
        }
        if (pastMapping.year == null) {
            return 1.0f
        }
        val currentYear = timeProvider.now().year
        val timePassed = currentYear - pastMapping.year
        if (timePassed <= 1) {
            return 1.0f
        }

        return Math.E.pow((-timePassed + 1).toDouble()).toFloat()

    }

    private fun scoreVsOne(matches: Set<Match>, pastMatches: Map<PersonId, PersonId>): Int {
        val numberOfDuplicates = matches.count { match -> pastMatches[match.gifter] == match.giftee }
        return numberOfDuplicates * (maxScore)

    }
}