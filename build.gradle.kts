plugins {
    kotlin("jvm") version "2.2.0"
    `maven-publish`
}

group = "kr.eme.prcMission"
version = "1.0.14"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.mireu9275:PRCMoney:v1.0.3")
    compileOnly("com.github.mireu9275:PRCShop:v1.1.1")
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))
}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.jar {
    archiveFileName = "${project.name}-${project.version}.jar"
    manifest {
        attributes["Main-Class"] = "kr.eme.prcMission.PRCMission"
    }
}

tasks.register<Copy>("copyToDesktop") {
    from(tasks.jar)
    into(File(System.getProperty("user.home"), "Desktop"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}