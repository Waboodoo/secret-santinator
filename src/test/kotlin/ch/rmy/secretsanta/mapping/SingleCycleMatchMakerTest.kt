package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.mapping.TestData.PERSON1
import ch.rmy.secretsanta.mapping.TestData.PERSON2
import ch.rmy.secretsanta.mapping.TestData.PERSON3
import ch.rmy.secretsanta.mapping.TestData.PERSON4
import ch.rmy.secretsanta.mapping.TestData.PERSON5
import ch.rmy.secretsanta.mapping.TestData.PERSON6
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
            PERSON1, PERSON2, PERSON3, PERSON4, PERSON5, PERSON6,
        )
        val matches = matchMaker.run(people)

        assertEquals(
            setOf(
                Match(PERSON1, PERSON2),
                Match(PERSON2, PERSON3),
                Match(PERSON3, PERSON6),
                Match(PERSON4, PERSON1),
                Match(PERSON5, PERSON4),
                Match(PERSON6, PERSON5),
            ),
            matches,
        )
    }
}