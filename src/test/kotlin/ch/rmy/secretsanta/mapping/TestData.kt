package ch.rmy.secretsanta.mapping

import ch.rmy.secretsanta.people.Person

object TestData {
    val PERSON1 = person("1")
    val PERSON2 = person("2")
    val PERSON3 = person("3")
    val PERSON4 = person("4")
    val PERSON5 = person("5")
    val PERSON6 = person("6")
    val PERSON7 = person("7")
    val PERSON8 = person("8")


    private fun person(id: String) = Person(id = id, name = "Person$id", email = "person$id@example.com")
}