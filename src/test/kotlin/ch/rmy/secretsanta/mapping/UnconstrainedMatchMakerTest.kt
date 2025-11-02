package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.mapping.TestData.PERSON1
import ch.rmy.secretsanta.mapping.TestData.PERSON2
import ch.rmy.secretsanta.mapping.TestData.PERSON3
import ch.rmy.secretsanta.mapping.TestData.PERSON4
import ch.rmy.secretsanta.mapping.TestData.PERSON5
import ch.rmy.secretsanta.mapping.TestData.PERSON6
import ch.rmy.secretsanta.mapping.TestData.PERSON7
import ch.rmy.secretsanta.mapping.TestData.PERSON8
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class UnconstrainedMatchMakerTest {
    @Test
    fun `matches with 8 people`() {
        val matchMaker = UnconstrainedMatchMaker(
            random = mockk {
                every { nextInt(1) } returns 0
            },
            subdivisionGenerator = mockk {
                every { generate(n = 8) } returns setOf(
                    listOf(2, 3, 3),
                )
            },
            delegate = DeterministicSingleCycleMatchMaker(),
        )
        val people = setOf(
            PERSON1,
            PERSON2,
            PERSON3,
            PERSON4,
            PERSON5,
            PERSON6,
            PERSON7,
            PERSON8,
        )
        val matches = matchMaker.run(people)

        assertEquals(
            setOf(
                Match(PERSON1, PERSON2),
                Match(PERSON2, PERSON1),
                Match(PERSON3, PERSON4),
                Match(PERSON4, PERSON5),
                Match(PERSON5, PERSON3),
                Match(PERSON6, PERSON7),
                Match(PERSON7, PERSON8),
                Match(PERSON8, PERSON6),
            ),
            matches,
        )
    }
}
