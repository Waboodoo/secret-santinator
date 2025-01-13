package ch.rmy.secretsanta

import ch.rmy.secretsanta.email.EmailMatchHandler
import ch.rmy.secretsanta.people.PeopleProvider
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
            EmailMatchHandler()
        },
    )
        .run()
}

private fun verifyNotRunYet() {
    val file = File(LAST_RUN_FILE)
    if (file.exists()) {
        error("Already done! Delete the $LAST_RUN_FILE file to allow another run.")
    } else {
        file.writeText(Instant.now().toString())
    }
}
