package ch.rmy.secretsanta.email

import org.simplejavamail.api.mailer.Mailer as SimpleMailer
import org.simplejavamail.api.mailer.config.TransportStrategy
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.MailerBuilder

class Mailer(
    serverConfig: ServerConfig,
) {
    private val mailer: SimpleMailer = MailerBuilder
        .withSMTPServer(serverConfig.host, serverConfig.port, serverConfig.username, serverConfig.password)
        .withTransportStrategy(TransportStrategy.SMTP_TLS)
        .buildMailer()

    fun sendEmail(
        sender: EmailContact,
        receiver: EmailContact,
        subject: String,
        htmlBody: String,
    ) {
        val email = EmailBuilder.startingBlank()
            .to(receiver.name, receiver.emailAddress)
            .from(sender.name, sender.emailAddress)
            .withSubject(subject)
            .withHTMLText(htmlBody)
            .buildEmail()

        mailer.sendMail(email)
            .join()
    }
}
