package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.TestData.PERSON1
import ch.rmy.secretsanta.mapping.TestData.PERSON2
import ch.rmy.secretsanta.mapping.TestData.PERSON3
import ch.rmy.secretsanta.mapping.TestData.PERSON4
import ch.rmy.secretsanta.mapping.TestData.PERSON5
import ch.rmy.secretsanta.mapping.TestData.PERSON6
import ch.rmy.secretsanta.mapping.TestData.PERSON7
import ch.rmy.secretsanta.mapping.TestData.PERSON8
import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.mapping.Mapping
import ch.rmy.secretsanta.TimeProvider
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import java.time.LocalDate

class VarietyScorerTest {

    @Test
    fun `Scores correctly against recent full match`() {
        val matches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON1),
        )

        val mapping = Mapping(
            matches = matches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
            year = 2024
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = TimeProvider { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-10, score)

    }

    @Test
    fun `Scores correctly against not so recent full match`() {
        val matches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON1),
        )

        val mapping = Mapping(
            matches = matches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
            year = 2023
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = TimeProvider { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-6, score)

    }

    @Test
    fun `Scores correctly against multiple matches`() {
        val matches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON1),
        )
        val partialmatches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON1),
        )

        val mapping = Mapping(
            matches = matches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
            year = 2024
        )
        val mapping2 = Mapping(
            matches = partialmatches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
            year = 2023
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping, mapping2),
            timeProvider = TimeProvider { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-12, score)

    }

    @Test
    fun `Scores correctly against full match without year`() {
        val matches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON1),
        )

        val mapping = Mapping(
            matches = matches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = TimeProvider { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-10, score)

    }

    @Test
    fun `Scores correctly against partial match without year`() {
        val matches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON1),
        )
        val partialmatches = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON1),
        )

        val mapping = Mapping(
            matches = partialmatches.associate { (gifter, giftee) ->
                gifter.id to giftee.id
            },
        )


        val scorer = VarietyScorer(
            mappings = setOf(mapping),
            timeProvider = TimeProvider { LocalDate.of(2025, 12, 12) }
        )

        var score = scorer.score(matches)
        assertEquals(-4, score)

    }



}