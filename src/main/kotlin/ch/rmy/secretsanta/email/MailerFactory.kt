package ch.rmy.secretsanta.email

class MailerFactory {
    fun create(serverConfig: ServerConfig): Mailer =
        Mailer(serverConfig)
}
