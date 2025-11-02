package ch.rmy.secretsanta.email

import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.TimeProvider
import ch.rmy.secretsanta.people.Person
import java.time.LocalDate

class EmailMappingHandler(
    private val messageConfig: EmailMessageConfig,
    private val mailer: Mailer,
    private val timeProvider: TimeProvider = TimeProvider.default,
) : MappingHandler {
    private val sender = with(messageConfig) {
        EmailContact(senderName, senderAddress)
    }

    override fun handle(matches: Map<Person, Person>) {
        matches.forEach { (gifter, giftee) ->
            mailer.sendEmail(
                sender,
                receiver = gifter.toEmailContact(),
                subject = messageConfig.subject.rendered(gifter, giftee),
                htmlBody = messageConfig.htmlBody.rendered(gifter, giftee),
            )
        }
    }

    private fun Person.toEmailContact() =
        EmailContact(name, email)

    private fun String.rendered(gifter: Person, giftee: Person): String =
        replace("{gifter.name}", gifter.name)
            .replace("{giftee.name}", giftee.name)
            .replace("{gifter.id}", gifter.id)
            .replace("{year}", timeProvider.now().year.toString())
}
