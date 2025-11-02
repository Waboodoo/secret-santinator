package ch.rmy.secretsanta.email

import ch.rmy.secretsanta.mapping.Match
import ch.rmy.secretsanta.MappingHandler
import ch.rmy.secretsanta.people.Person
import java.time.LocalDate

class EmailMappingHandler(
    private val messageConfig: EmailMessageConfig,
    private val mailer: Mailer,
) : MappingHandler {
    private val sender = with(messageConfig) {
        EmailContact(senderName, senderAddress)
    }

    override fun handle(matches: Set<Match>) {
        matches.forEach { match ->
            mailer.sendEmail(
                sender,
                receiver = match.gifter.toEmailContact(),
                subject = messageConfig.subject.rendered(match),
                htmlBody = messageConfig.htmlBody.rendered(match),
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
