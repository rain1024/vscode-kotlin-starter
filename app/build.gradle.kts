plugins {
    kotlin("jvm") version "1.9.10"
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit 4 for testing.
    testImplementation("junit:junit:4.12")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.google.api:api-common:2.17.1")
}

application {
    // Define the main class for the application.
    mainClass.set("example.Example")
}