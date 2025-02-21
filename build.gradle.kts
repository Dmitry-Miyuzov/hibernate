plugins {
    id("java")
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




    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}