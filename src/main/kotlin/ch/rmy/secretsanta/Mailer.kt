package ch.rmy.secretsanta

import org.simplejavamail.api.mailer.Mailer as SimpleMailer
import org.simplejavamail.api.mailer.config.TransportStrategy
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.MailerBuilder

class Mailer {

    private val mailer: SimpleMailer = MailerBuilder
        .withSMTPServer("TODO", 993, "TODO", "TODO")
        .withTransportStrategy(TransportStrategy.SMTP_TLS)
        .withDebugLogging(true)
        .buildMailer()

    fun sendEmail(
        address: String,
        name: String,
        subject: String,
        htmlMessage: String,
    ) {
        val email = EmailBuilder.startingBlank()
            .to(name, address)
            .from("TODO")
            .withSubject(subject)
            .withHTMLText(htmlMessage)
            .buildEmail()

        mailer.sendMail(email)
            .join()
    }
}
