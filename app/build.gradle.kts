plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    buildToolsVersion(Config.Android.buildToolsVersion)

    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
}

dependencies {
    implementation(project(":opencv"))

    implementation(Config.Tools.kotlinStd)
    implementation(Config.Tools.ktxCore)

    implementation(Config.Android.appcompat)
    implementation(Config.Android.constraintLayout)
    implementation(Config.Android.navigationFragment)
    implementation(Config.Android.navigationUi)
    implementation(Config.Android.legacySupport)
    implementation(Config.Android.viewModel)
    implementation(Config.Android.material)

    implementation(Config.ThirdPartyLibs.picasso)
    implementation(Config.ThirdPartyLibs.okhttp)
    implementation(Config.ThirdPartyLibs.retrofit)
    implementation(Config.ThirdPartyLibs.koinCore)

    testImplementation(Config.TestingLibs.junit)
    testImplementation(Config.TestingLibs.androidxJunit)

    androidTestImplementation(Config.TestingLibs.espressoCore)
}
