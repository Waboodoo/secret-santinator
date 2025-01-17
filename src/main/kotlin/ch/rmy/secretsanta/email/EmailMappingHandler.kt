package ch.rmy.secretsanta.email

import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.people.Person
import java.time.LocalDate

class EmailMappingHandler(
    private val configProvider: EmailConfigProvider = EmailConfigProvider(),
    mailerFactory: MailerFactory = MailerFactory(),
) : MappingHandler {
    private val mailer = mailerFactory.create(configProvider.server)
    private val sender = with(configProvider.message) {
        EmailContact(senderName, senderAddress)
    }

    override fun handle(mapping: Set<Match>) {
        mapping.forEach { match ->
            mailer.sendEmail(
                sender,
                receiver = match.gifter.toEmailContact(),
                subject = configProvider.message.subject.rendered(match),
                htmlBody = configProvider.message.htmlBody.rendered(match),
            )
        }
    }

    private fun Person.toEmailContact() =
        EmailContact(name, email)

    private fun String.rendered(match: Match): String =
        replace("{gifter.name}", match.gifter.name)
            .replace("{giftee.name}", match.giftee.name)
            .replace("{gifter.id}", match.gifter.id)
            .replace("{year}", LocalDate.now().year.toString())
}
