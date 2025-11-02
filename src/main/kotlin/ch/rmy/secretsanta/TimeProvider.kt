package ch.rmy.secretsanta

import java.time.LocalDate

fun interface TimeProvider {
    fun now(): LocalDate

    companion object {
        val default = TimeProvider { LocalDate.now() }
    }
}