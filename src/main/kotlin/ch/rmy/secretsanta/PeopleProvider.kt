package ch.rmy.secretsanta

class PeopleProvider {
    fun getPeople(): Set<Person> {
        return setOf(
            Person("a", "Alice", "alice@example.com"),
            Person("b", "Bob", "bob@example.com"),
            Person("c", "Carol", "carol@example.com"),
            Person("d", "Dave", "dave@example.com"),
        )
    }
}