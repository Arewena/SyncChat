import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    implementation("net.dv8tion:JDA:3.0.0_156")
    implementation("com.github.JDA-Applications:Kotlin-JDA:master-SNAPSHOT")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}