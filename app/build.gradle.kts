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
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("main").jniLibs.srcDirs("src/main/jniLibs")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":opencv"))

    implementation(Config.Tools.kotlinStd)
    implementation(Config.Tools.ktxCore)

    implementation(Config.Android.appcompat)
    implementation(Config.Android.material)
    implementation(Config.Android.constraintLayout)
    implementation(Config.Android.viewModel)
    implementation(Config.Android.room)
    kapt(Config.Android.roomCompiler)
    implementation(Config.Android.navigationFragment)
    implementation(Config.Android.navigationUi)
    implementation(Config.Android.cameraXCore)
    implementation(Config.Android.cameraX)
    implementation(Config.Android.cameraXLifecycle)
    implementation(Config.Android.cameraXView)

    implementation(Config.ThirdPartyLibs.picasso)
    implementation(Config.ThirdPartyLibs.dagger)
    kapt(Config.ThirdPartyLibs.daggerCompiler)
}
