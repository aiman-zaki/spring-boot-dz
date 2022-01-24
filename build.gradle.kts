import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

extra["springCloudVersion"] = "2021.0.0"
extra["keycloakVersion"] = "15.0.2"
extra["kotlinVersion"] = "1.6.10"
extra["springBootVersion"] = "2.6.2"

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    idea
    kotlin("kapt") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.2.1"
}

group = "com.github.aiman-zaki"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/release") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-security:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-webflux:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-cache:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:${property("springBootVersion")}")

    implementation("org.springframework.security:spring-security-oauth2-client:5.6.1")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin:3.1.0")
    // implementation("org.keycloak:keycloak-spring-boot-starter:15.0.2")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0-native-mt")

    implementation("org.postgresql:postgresql:42.3.1")
    implementation("com.opencsv:opencsv:5.5.2")

    // implementation("org.springframework.boot:spring-boot-starter-oauth2-client:${property("springBootVersion")}")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:${property("springBootVersion")}")

    // implementation("org.springframework.boot:spring-boot-starter-actuator:${property("springBootVersion")}")
    implementation("org.springframework.security:spring-security-oauth2-jose:5.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${property("springBootVersion")}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation(kotlin("test"))

    testImplementation("io.projectreactor:reactor-test:3.4.13")
    testImplementation("org.springframework.security:spring-security-test:5.5.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        // mavenBom("org.keycloak.bom:keycloak-adapter-bom:${property("keycloakVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

allOpen {
    annotations("javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embedabble")
}

noArg {
    annotations("javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embedabble")
}
