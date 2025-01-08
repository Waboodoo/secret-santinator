plugins {
    kotlin("jvm") version "2.0.21"
}

group = "ch.rmy.secretsanta"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.simplejavamail:simple-java-mail:8.12.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
