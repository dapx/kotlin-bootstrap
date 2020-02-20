import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version("1.3.61")
}

repositories {
    jcenter()
}

val junitVersion: String by project

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

kotlin {
    sourceSets["main"].kotlin.srcDir("src/main")
    sourceSets["main"].resources.srcDir("src/main/resources")
    sourceSets["test"].kotlin.srcDir("src/test")
    sourceSets["test"].resources.srcDir("src/test/resources")
}

application.mainClassName = "sample.project.Main"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform {
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    reports {
        junitXml.isEnabled = false
    }

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")

    maxParallelForks = Runtime.getRuntime().availableProcessors()
}
