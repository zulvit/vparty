plugins {
    java
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ru.zulvit"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.webjars:bootstrap:5.3.0")
    runtimeOnly("org.postgresql:postgresql")
    implementation("jakarta.validation:jakarta.validation-api:3.0.1")
    implementation("org.hibernate.validator:hibernate-validator:7.0.1.Final")

    implementation("org.projectlombok:lombok:1.18.28")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}