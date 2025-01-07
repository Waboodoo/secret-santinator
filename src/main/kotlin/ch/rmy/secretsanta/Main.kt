package ch.rmy.secretsanta

import java.io.File
import java.time.Instant

private const val LAST_RUN_FILE = "last_run.txt"

fun main() {
    val dryRun = true
    if (!dryRun) {
        verifyNotRunYet()
    }

    SecretSantinator(
        peopleProvider = PeopleProvider(),
        matchMaker = MatchMaker(),
        matchHandler = if (dryRun) {
            PrintMatchHandler
        } else {
            EmailMatchHandler(Mailer())
        },
    )
        .run()
}

private fun verifyNotRunYet() {
    val file = File(LAST_RUN_FILE)
    if (file.exists()) {
        error("Already done!")
    } else {
        file.writeText(Instant.now().toString())
    }
}
