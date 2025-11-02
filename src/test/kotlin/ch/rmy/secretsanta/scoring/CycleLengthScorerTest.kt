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
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class CycleLengthScorerTest {

    @Test
    fun `Scores correctly`() {
        val fullCycleMapping = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON1),
        )
        val tinyCycleMapping = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON1),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON5),
            Match(PERSON5, PERSON3),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON6),
        )
        val midCycleMapping = setOf(
            Match(PERSON1, PERSON2),
            Match(PERSON2, PERSON3),
            Match(PERSON3, PERSON4),
            Match(PERSON4, PERSON1),
            Match(PERSON5, PERSON6),
            Match(PERSON6, PERSON7),
            Match(PERSON7, PERSON8),
            Match(PERSON8, PERSON5),
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