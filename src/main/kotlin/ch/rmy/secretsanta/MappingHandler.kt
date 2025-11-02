package ch.rmy.secretsanta

import ch.rmy.secretsanta.people.Person

interface MappingHandler {
    fun handle(matches: Map<Person, Person>)
}
