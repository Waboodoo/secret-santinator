package ch.rmy.secretsanta.mapping

class SubdivisionGenerator {
    fun generate(n: Int): Set<List<Int>> {
        require(n >= 2)
        return produceSubdivisions(n)
    }

    private fun produceSubdivisions(n: Int): Set<List<Int>> {
        if (n == 2 || n == 3) {
            return setOf(listOf(n))
        }

        val subDivisions = mutableSetOf(listOf(n))
        for (i in 2..(n / 2)) {
            produceSubdivisions(n = n - i).forEach { subsubdivision ->
                subDivisions.add(
                    (subsubdivision + listOf(i)).sorted()
                )
            }
        }
        return subDivisions
    }
}
