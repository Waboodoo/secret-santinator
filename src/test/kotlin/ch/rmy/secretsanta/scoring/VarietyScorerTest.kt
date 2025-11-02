package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.TimeProvider
import kotlin.test.Test
import kotlin.test.assertEquals
import java.time.LocalDate

class VarietyScorerTest {

    @Test
    fun `Scores correctly against recent full match`() {
        val matches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )

        val mapping = Mapping(
            matches = matches.associate { it.gifter to it.giftee },
            year = 2024
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-70, score)

    }

    @Test
    fun `Scores correctly against not so recent full match`() {
        val matches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )

        val mapping = Mapping(
            matches = matches.associate { it.gifter to it.giftee },
            year = 2023
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = TimeProvider { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-19, score)

    }

    @Test
    fun `Scores correctly against multiple matches`() {
        val matches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )
        val partialmatches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "A"),
        )

        val mapping = Mapping(
            matches = matches.associate { it.gifter to it.giftee },
            year = 2024
        )
        val mapping2 = Mapping(
            matches = partialmatches.associate { it.gifter to it.giftee },
            year = 2023
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping, mapping2),
            timeProvider = { LocalDate.of(2025, 12, 12) }
        )

        val score = scorer.score(matches)
        assertEquals(-70, score)

    }

    @Test
    fun `Scores correctly against full match without year`() {
        val matches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )

        val mapping = Mapping(
            matches = matches.associate { it.gifter to it.giftee },
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = { LocalDate.of(2025, 12, 12) }
        )

        val score = scorer.score(matches)
        assertEquals(-70, score)

    }

    @Test
    fun `Scores correctly against full match with custom weight`() {
        val matches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )

        val mapping = Mapping(
            matches = matches.associate { it.gifter to it.giftee },
            scoreMultiplier = 3.0f,
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = { LocalDate.of(2025, 12, 12) }
        )

        val score = scorer.score(matches)
        assertEquals(-230, score)

    }


    @Test
    fun `Scores correctly against partial match without year`() {
        val matches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )
        val partialmatches = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "A"),
        )

        val mapping = Mapping(
            matches = partialmatches.associate { it.gifter to it.giftee },
        )

        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = { LocalDate.of(2025, 12, 12) }
        )

        val score = scorer.score(matches)
        assertEquals(-20, score)
    }

}
