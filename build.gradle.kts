plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"

}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")
    implementation("com.google.code.gson:gson:2.13.2")
}

application {
    mainClass = "it.unicam.cs.mpgc.rpg129097.MainApp"
}

javafx{
    version= "24.0.1"
    modules("javafx.controls", "javafx.fxml", "javafx.media")
}