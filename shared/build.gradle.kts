plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    //Kotlin serialization
    kotlin("plugin.serialization") version "1.9.10"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    //Dependencies versions
    val coroutinesVersion = "1.6.4"
    val ktorVersion = "2.2.1"
    val koinVersion = "3.3.2"

    sourceSets {

        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)

            implementation("co.touchlab:kermit:2.0.3") //Add latest version
            //Use api so that the android app can use it as well
            api("io.insert-koin:koin-core:$koinVersion")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }




        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:$ktorVersion")
            api("io.insert-koin:koin-android:$koinVersion")
        }


        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }


    }
}

android {
    namespace = "com.iamer.moviessample.kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
