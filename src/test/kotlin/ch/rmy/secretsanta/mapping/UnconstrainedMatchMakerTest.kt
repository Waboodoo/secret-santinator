package ch.rmy.secretsanta.mapping

import io.mockk.every
import io.mockk.mockk
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class UnconstrainedMatchMakerTest {
    @Test
    fun `matches with 8 people`() {
        val matchMaker = UnconstrainedMatchMaker(
            random = Random(seed = 1234),
            subdivisionGenerator = mockk {
                every { generate(n = 8) } returns setOf(
                    listOf(2, 3, 3),
                )
            },
            delegate = DeterministicSingleCycleMatchMaker(),
        )
        val people = setOf(
            "A", "B", "C", "D", "E", "F", "G", "H",
        )
        val matches = matchMaker.run(people)

        assertEquals(
            setOf(
                Match("A", "C"),
                Match("B", "H"),
                Match("C", "A"),
                Match("D", "E"),
                Match("E", "F"),
                Match("F", "D"),
                Match("G", "B"),
                Match("H", "G"),
            ),
            matches,
        )
    }
}
