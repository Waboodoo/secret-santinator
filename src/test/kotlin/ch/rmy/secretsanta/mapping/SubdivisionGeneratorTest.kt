package ch.rmy.secretsanta.mapping

import kotlin.test.Test
import kotlin.test.assertEquals

class SubdivisionGeneratorTest {

    @Test
    fun `generate subdivisions`() {
        val generator = SubdivisionGenerator()
        val subdivisions = generator.generate(n = 8)
        assertEquals(
            setOf(
                listOf(8),
                listOf(2, 6),
                listOf(2, 2, 4),
                listOf(2, 2, 2, 2),
                listOf(2, 3, 3),
                listOf(3, 5),
                listOf(4, 4),
            ),
            subdivisions,
        )
    }

}