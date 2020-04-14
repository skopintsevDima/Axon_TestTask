plugins {
    id("com.android.library")
}

android {
    compileSdkVersion(Config.OpenCV.compileSdkVersion)
    buildToolsVersion(Config.OpenCV.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Config.OpenCV.minSdkVersion)
        targetSdkVersion(Config.OpenCV.targetSdkVersion)
        versionCode = Config.OpenCV.versionCode
        versionName = Config.OpenCV.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").jniLibs.srcDirs("src/main/jniLibs")
    }
}