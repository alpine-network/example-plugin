plugins {
    java
}

group = "dev.tomwmth"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://lib.alpn.cloud/releases/")
    maven("https://lib.alpn.cloud/snapshots/")
    maven("https://repo.viaversion.com/")
}


dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("co.crystaldev:alpinecore:0.4.10-SNAPSHOT")
    compileOnly("com.viaversion:viaversion-api:5.2.1")

    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

// Run Gradle with Java 21
plugins.withId("java") {
    the<JavaPluginExtension>().toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// Target Java 8+
tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
    options.encoding = "UTF-8"
}

tasks.named<Jar>("jar") {
    // Rename the jar to 'ExamplePlugin-1.0.0.jar'
    archiveFileName.set("${rootProject.name}-${project.version}.jar")
}

tasks.processResources {
    // Replace the '${version}' in 'src/main/resources/plugin.yml'
    val pluginVersion = version.toString()
    filesMatching("plugin.yml") {
        expand("version" to pluginVersion)
    }
}

java {
    withSourcesJar()
}
