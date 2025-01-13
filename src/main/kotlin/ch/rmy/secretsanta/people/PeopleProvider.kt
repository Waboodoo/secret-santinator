package ch.rmy.secretsanta.people

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import java.io.File

class PeopleProvider(
    configFile: File = File("people.yml"),
) {
    private val people = configFile
        .inputStream().use { stream ->
            Yaml.default.decodeFromStream<List<Person>>(stream)
        }
        .toSet()

    fun getPeople(): Set<Person> =
        people
}
