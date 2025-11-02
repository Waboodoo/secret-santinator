package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.scoring.Scorer
import kotlin.random.Random
import io.mockk.mockk
import io.mockk.every
import kotlin.test.Test
import kotlin.test.assertEquals

class ScoredMatchMakerTest {

    @Test
    fun `pickByWeight works`() {
        val matchMaker = ScoredMatchMaker(
            delegate = mockk(),
            scorer = mockk(),
            random = Random(seed = 1234),
        )

        val testlist = listOf(1, 2, 3, 4, 5, 6)
        val testWeights = listOf(0, 0, 0, 1, 0, 0)

        val result = matchMaker.pickByWeight(testlist, testWeights)
        assertEquals(4, result)

        val testWeights2 = listOf(1, 0, 0, 0, 0, 0)
        val result2 = matchMaker.pickByWeight(testlist, testWeights2)
        assertEquals(1, result2)

        val testWeights4 = listOf(0, 0, 0, 0, 0, 0)
        val result4 = matchMaker.pickByWeight(testlist, testWeights4)
        assertEquals(6, result4)

        val testWeights3 = listOf(-1, -1, -1, -1, -1, 1)
        val result3 = matchMaker.pickByWeight(testlist, testWeights3)
        assertEquals(6, result3)
    }

    @Test
    fun `matches with 6 people`() {
        val delegate = SingleCycleMatchMaker(
            random = Random(seed = 1234),
        )

        val scorer = mockk<Scorer> {
            every { score(any()) } returns 1 andThen 0
        }

        val matchMaker = ScoredMatchMaker(
            delegate,
            random = Random(seed = 1234),
            scorer = scorer,
        )

        val people = setOf(
            "A", "B", "C", "D", "E", "F",
        )
        val matches = matchMaker.run(people)

        assertEquals(
            setOf(
                Match("A", "B"),
                Match("B", "C"),
                Match("C", "F"),
                Match("D", "A"),
                Match("E", "D"),
                Match("F", "E"),
            ),
            matches,
        )
    }

}