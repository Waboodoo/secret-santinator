plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
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
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
