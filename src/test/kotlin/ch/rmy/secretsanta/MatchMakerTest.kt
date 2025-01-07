package ch.rmy.secretsanta

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class MatchMakerTest {

    @Test
    fun `matches with 6 people`() {
        val matchMaker = MatchMaker(
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

    companion object {
        val PERSON1 = person("1")
        val PERSON2 = person("2")
        val PERSON3 = person("3")
        val PERSON4 = person("4")
        val PERSON5 = person("5")
        val PERSON6 = person("6")

        private fun person(id: String) = Person(id = id, name = "", email = "")
    }
}