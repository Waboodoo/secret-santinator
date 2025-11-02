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
    private val maxNegativeScore: Int = -10,
) : Scorer {
    override fun score(matches: Set<Match>): Int {

        return mappings.fold(0) { score, pastMapping ->
            score + (calculateFactorFor(pastMapping.year) * scoreVsOne(matches, pastMapping.matches)).roundToInt()
        }
    }

    private fun calculateFactorFor(year: Int?): Float {
        if (year == null) {
            return 1.0f
        }
        val currentYear = timeProvider.now().year
        val timePassed = currentYear - year
        if (timePassed <= 1) {
            return 1.0f
        }

        return Math.E.pow((-timePassed+1).toDouble() / 2).toFloat()

    }

    private fun scoreVsOne(matches: Set<Match>, pastMatches: Map<PersonId, PersonId>): Int {
        var numberOfDuplicates = matches.count{ match -> pastMatches.get(match.gifter.id) == match.giftee.id }
        return ((numberOfDuplicates.toFloat() / matches.size.toFloat()) * maxNegativeScore).roundToInt()

    }
}