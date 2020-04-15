object Config {
    private object Versions {
        // Tools
        const val kotlinVersion = "1.3.71"
        const val androidGradleVersion = "3.5.3"
        const val ktxCoreVersion = "1.1.0"
        const val navigationVersion = "2.2.0"

        // Android
        const val appcompatVersion = "1.1.0"
        const val materialVersion = "1.2.0-alpha04"
        const val constraintLayoutVersion = "1.1.3"
        const val viewModelVersion = "2.2.0"
        const val roomVersion = "2.2.5"
        const val cameraXVersion = "1.0.0-beta02"
        const val cameraXViewVersion = "1.0.0-alpha09"
        const val pagingVersion = "2.1.2"

        // Third-party libs
        const val picassoVersion = "2.71828"
        const val daggerVersion = "2.27"
    }

    object Tools {
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradleVersion}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCoreVersion}"
    }

    object Android {
        const val buildToolsVersion = "29.0.2"
        const val minSdkVersion = 21
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val applicationId = "com.test.axontest"
        const val versionCode = 1
        const val versionName = "1.0"

        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
        const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
        const val cameraXCore = "androidx.camera:camera-core:${Versions.cameraXVersion}"
        const val cameraX = "androidx.camera:camera-camera2:${Versions.cameraXVersion}"
        const val cameraXLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraXVersion}"
        const val cameraXView = "androidx.camera:camera-view:${Versions.cameraXViewVersion}"
        const val paging = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
    }

    object OpenCV {
        const val buildToolsVersion = "29.0.2"
        const val minSdkVersion = 21
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val versionCode = 34100
        const val versionName = "3.4.10"
    }

    object ThirdPartyLibs {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    }
}