package ch.rmy.secretsanta

import ch.rmy.secretsanta.email.EmailMappingHandler
import ch.rmy.secretsanta.email.Mailer
import ch.rmy.secretsanta.mapping.SingleCycleMatchMaker
import ch.rmy.secretsanta.mapping.ScoredMatchMaker
import ch.rmy.secretsanta.mapping.UnconstrainedMatchMaker
import ch.rmy.secretsanta.output.StoreMappingHandler
import ch.rmy.secretsanta.people.PeopleProvider
import ch.rmy.secretsanta.scoring.CompositeScorer
import ch.rmy.secretsanta.scoring.CycleLengthScorer
import ch.rmy.secretsanta.scoring.VarietyScorer
import java.io.File
import java.time.Instant
import kotlin.let
import kotlin.system.exitProcess

private const val ARG_DRY = "-dry"
private const val CONFIG_PREFIX = "config-"

fun main(args: Array<String>) {
    try {
        val dryRun = args.any { it == ARG_DRY }
        val configName = args.firstOrNull { it != ARG_DRY }
            ?.removePrefix(CONFIG_PREFIX)
            ?.let(::sanitizeConfigName)
            ?: error("No valid config name provided")

        //verifyNotRunYet(configName)

        val configDir = File(CONFIG_PREFIX + configName)
        val mappingsDir = File(ConfigFiles.MAPPINGS_DIR, configName)
        val pastMappings = PastMappingReader(
            mappingsDirectory = mappingsDir,
        ).read()

        val matchMaker = ScoredMatchMaker(
            delegate = UnconstrainedMatchMaker(
                delegate = SingleCycleMatchMaker(),
            ),
            scorer = CompositeScorer(
                scorers = listOf(
                    CycleLengthScorer(),
                    VarietyScorer(
                        mappings = pastMappings,
                    ),
                ),
            ),
        )

        SecretSantinator(
            peopleProvider = PeopleProvider(
                configFile = File(configDir, ConfigFiles.PEOPLE)
            ),
            matchMaker = matchMaker,
            mappingHandlers = buildList {
                add(
                    StoreMappingHandler(
                        mappingsDirectory = mappingsDir,
                    )
                )
                if (dryRun) {
                    add(PrintMappingHandler)
                } else {
                    add(
                        EmailMappingHandler(
                            messageConfig = File(configDir, ConfigFiles.EMAIL_MESSAGE).readYaml(),
                            mailer = Mailer(
                                File(configDir, ConfigFiles.EMAIL_SERVER).readYaml()
                            )
                        )
                    )
                }
            },
        )
            .run()
    } catch (e: Exception) {
        if (e is IllegalStateException || e is IllegalArgumentException) {
            println(e.message)
            exitProcess(1)
        }
        e.printStackTrace()
        exitProcess(2)
    }
}

private fun verifyNotRunYet(configName: String) {
    val file = File("last_run-$configName.txt")
    if (file.exists()) {
        error("Already done! Delete the ${file.name} file to allow another run.")
    } else {
        file.writeText(Instant.now().toString())
    }
}

private fun sanitizeConfigName(configName: String) =
    configName.filter { it.isLetterOrDigit() }