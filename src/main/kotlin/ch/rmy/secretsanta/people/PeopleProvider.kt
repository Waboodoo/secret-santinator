package ch.rmy.secretsanta.people

import ch.rmy.secretsanta.readYaml
import java.io.File

class PeopleProvider(
    private val configFile: File,
) {
    fun getPeople(): Set<Person> =
        configFile.readYaml<List<Person>>()
            .toSet()
}
