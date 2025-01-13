plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.4.20"
}

group = "ch.rmy.secretsanta"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.simplejavamail:simple-java-mail:8.12.4")
    implementation("com.charleskorn.kaml:kaml:0.67.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
