plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    `maven-publish`
    signing
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.fragment:fragment-ktx:1.3.0-rc01")

    //logs
    implementation("com.jakewharton.timber:timber:4.7.1")
    //compressor
    implementation("id.zelory:compressor:3.0.0")

    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"])

                // You can then customize attributes of the publication as shown below.
                groupId = "ro.dragossusi.android"
                artifactId = "imagepicker"
                version = Versions.app

                val scmName = "AndroidImagePicker"
                pom {
                    name.set("AndroidImagePicker")
                    description.set("Android Image picker")
                    url.set("https://github.com/dragossusi/$scmName/")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("dragossusi")
                            name.set("Dragos Rachieru")
                            email.set("rachierudragos97@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/dragossusi/$scmName.git")
                        developerConnection.set("scm:git:ssh://github.com/dragossusi/$scmName.git")
                        url.set("https://github.com/dragossusi/$scmName/")
                    }
                }
            }
        }
        repositories {
            maven {
                name = "sonatype"
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = project.property("sonatype.username").toString()
                    password = project.property("sonatype.password").toString()
                }
            }
        }
    }

    signing {
        sign(publishing.publications["release"])
    }
}