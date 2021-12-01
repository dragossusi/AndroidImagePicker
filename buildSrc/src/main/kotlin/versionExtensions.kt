import org.gradle.api.Project

val Project.kotlinVersion: String
    get() {
        if (hasProperty("sevens.kotlin.version")) {
            property("sevens.kotlin.version")?.let {
                return it.toString()
            }
        }
        return Versions.Fallback.kotlin
    }

val Project.coroutinesVersion: String
    get() {
        if (hasProperty("sevens.kotlin.coroutines.version")) {
            property("sevens.kotlin.coroutines.version")?.let {
                return it.toString()
            }
        }
        return Versions.Fallback.coroutines
    }

val Project.androidVersion: String
    get() {
        if (hasProperty("sevens.android.gradle.version")) {
            property("sevens.android.gradle.version")?.let {
                return it.toString()
            }
        }
        return Versions.Fallback.android
    }