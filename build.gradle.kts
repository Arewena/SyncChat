plugins {
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io/")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.dv8tion:JDA:5.0.0-beta.1")
    implementation("com.github.minndevelopment:jda-ktx:34b55c0")
    implementation("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}