plugins {
    java
    id("io.freefair.lombok") version "6.3.0"
    id("java-library") //доступен метод api в dependencies
}

group = "ru.test.ryazan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
// https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:6.6.8.Final")
    // https://mvnrepository.com/artifact/org.aeonbits.owner/owner
    implementation("org.aeonbits.owner:owner:1.0.12")
    implementation("org.reflections:reflections:0.9.12")


    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("com.zaxxer:HikariCP:6.2.1")

    //----------------------------- Логирование ----------------------------------
    //Абстракция для логирования (фасад), который позволяет использовать разные реализации логгер-фрейморков. (Logback, Log4j и другие).
    //Сам по себе работать не нужен - нужна реализация.
    implementation("org.slf4j:slf4j-api:2.0.16")

    //Реализация для фасада логирования, включает в себя нужный logback-core.
    //    Для настройки используется файл logback.xml.
    //    Аннотация для иньекции логгера: @Slf4j - от ломбока.
    implementation("ch.qos.logback:logback-classic:1.5.16")
    //----------------------------------------------------------------------------




    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}