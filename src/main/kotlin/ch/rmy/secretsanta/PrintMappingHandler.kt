package ch.rmy.secretsanta

import ch.rmy.secretsanta.people.Person

object PrintMappingHandler : MappingHandler {
    override fun handle(mapping: Set<Match>) {
        mapping.forEach { (gifter, giftee) ->
            println("${gifter.formatted()} \t => \t ${giftee.formatted()}")
        }
    }

    private fun Person.formatted() =
        "$name ($id)"
}
