plugins {
    java
}

group = "de.skyslycer"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}