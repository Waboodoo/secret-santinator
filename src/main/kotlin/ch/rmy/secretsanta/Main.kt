package ch.rmy.secretsanta

import ch.rmy.secretsanta.email.EmailMappingHandler
import ch.rmy.secretsanta.output.StoreMappingHandler
import ch.rmy.secretsanta.people.PeopleProvider
import java.io.File
import java.time.Instant

private const val ARG_DRY = "-dry"
private const val LAST_RUN_FILE = "last_run.txt"

fun main(args: Array<String>) {
    val dryRun = args.any { it == ARG_DRY }
    if (!dryRun) {
        verifyNotRunYet()
    }

    SecretSantinator(
        peopleProvider = PeopleProvider(),
        matchMaker = MatchMaker(),
        mappingHandlers = buildList {
            if (dryRun) {
                add(PrintMappingHandler)
            } else {
                add(StoreMappingHandler())
                add(EmailMappingHandler())
            }
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
