plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}


val Project.androidVersion: String
    get() {
        if (hasProperty("sevens.android.gradle.version")) {
            property("sevens.android.gradle.version")?.let {
                return it.toString()
            }
        }
        return "7.0.3"
    }

dependencies {
    implementation("com.android.tools.build:gradle:$androidVersion")
}