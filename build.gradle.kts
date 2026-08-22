plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")
}

application {
    mainClass = "it.unicam.cs.mpgc.rpg129097.MainApp"
}
