package ch.rmy.secretsanta.people

import ch.rmy.secretsanta.ConfigFiles
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import java.io.File

class PeopleProvider(
    configFile: File = File(ConfigFiles.PEOPLE),
) {
    private val people = configFile
        .inputStream().use { stream ->
            Yaml.default.decodeFromStream<List<Person>>(stream)
        }
        .toSet()

    fun getPeople(): Set<Person> =
        people
}
