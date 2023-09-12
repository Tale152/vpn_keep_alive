plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.1-jre")
    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    // OkHttp
    implementation("com.squareup.okhttp:okhttp:2.7.5")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "vpn_keep_alive.App"
    }
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveFileName.set("vpn_keep_alive.jar")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "vpn_keep_alive.App"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

application {
    // Define the main class for the application.
    mainClass.set("vpn_keep_alive.App")
}
