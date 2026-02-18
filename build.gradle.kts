plugins {
    kotlin("jvm") version "2.2.0"
    `maven-publish`
}

group = "kr.eme.prcMission"
version = "1.0.9"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven(url="https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.mireu9275:PRCMoney:v1.0.3")
    compileOnly("com.github.mireu9275:PRCShop:v1.0.13")
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
    destinationDirectory = file("\\\\172.30.1.38\\공유폴더\\paper 1.21.4\\plugins")
    manifest {
        attributes["Main-Class" ] = "kr.eme.prcMission.PRCMission"
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = project.group.toString()      // ✅ group = "kr.eme.semiMission"
            artifactId = "PRCMission"              // ✅ artifactId
            version = project.version.toString()    // ✅ version = "1.0.0"
        }
    }
    repositories {
        mavenLocal() // ✅ publishToMavenLocal 실행 시 ~/.m2/repository 로 배포
    }
}
