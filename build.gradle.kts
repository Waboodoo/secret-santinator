plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
    application
}

group = "ch.rmy.secretsanta"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.simplejavamail:simple-java-mail:8.12.4")
    implementation("com.charleskorn.kaml:kaml:0.67.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.14.6")
}

application {
    mainClass.set("ch.rmy.secretsanta.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
