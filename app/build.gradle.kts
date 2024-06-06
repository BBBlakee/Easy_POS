val versionMajor = 1
val versionMinor = 1
val versionPatch = 0

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.pos_moneylist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pos_moneylist"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        create("beta") {
            initWith(getByName("release"))
            versionNameSuffix = "-beta"
            applicationIdSuffix = ".beta"
        }
        debug {
            isDebuggable = true
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }

        compileOptions {

            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }

        buildFeatures {
            compose = true
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {

        implementation(libs.paperDB)

        implementation(libs.androidx.ktx)
        implementation(libs.androidx.runtime.ktx)
        implementation(libs.androidx.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.grapics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.navigation)
        //testImplementation("junit:junit:4.13.2")
        //androidTestImplementation("androidx.test.ext:junit:1.1.5")
        //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        //androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.02"))
        //androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.3")
        //debugImplementation("androidx.compose.ui:ui-tooling:1.6.3")
        //debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.3")
    }
}