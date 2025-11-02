package ch.rmy.secretsanta.mapping

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class SingleCycleMatchMakerTest {

    @Test
    fun `matches with 6 people`() {
        val matchMaker = SingleCycleMatchMaker(
            random = Random(seed = 1234),
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