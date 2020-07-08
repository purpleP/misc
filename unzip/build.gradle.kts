
plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

application {
    mainClassName = "unzip.App"
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
