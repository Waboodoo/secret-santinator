package ch.rmy.secretsanta.scoring

import ch.rmy.secretsanta.mapping.Match
import kotlin.test.Test
import kotlin.test.assertEquals

class CycleLengthScorerTest {

    @Test
    fun `scores correctly`() {
        val fullCycleMapping = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "A"),
        )
        val tinyCycleMapping = setOf(
            Match("A", "B"),
            Match("B", "A"),
            Match("C", "D"),
            Match("D", "E"),
            Match("E", "C"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "F"),
        )
        val midCycleMapping = setOf(
            Match("A", "B"),
            Match("B", "C"),
            Match("C", "D"),
            Match("D", "A"),
            Match("E", "F"),
            Match("F", "G"),
            Match("G", "H"),
            Match("H", "E"),
        )

        val scorer = CycleLengthScorer()

        var score = scorer.score(fullCycleMapping)
        assertEquals(10, score)

        score = scorer.score(tinyCycleMapping)
        assertEquals(0, score)

        score = scorer.score(midCycleMapping)
        assertEquals(3, score)
    }

}