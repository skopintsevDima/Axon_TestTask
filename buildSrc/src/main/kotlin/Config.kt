object Config {
    private object Versions {
        // Tools
        const val kotlinVersion = "1.3.61"
        const val androidGradleVersion = "3.5.3"
        const val ktxCoreVersion = "1.1.0"
        const val navigationVersion = "2.2.0"

        // Android
        const val appcompatVersion = "1.1.0"
        const val materialVersion = "1.2.0-alpha04"
        const val constraintLayoutVersion = "1.1.3"
        const val legacySupportVersion = "1.0.0"
        const val viewModelVersion = "2.2.0"

        // Third-party libs
        const val picassoVersion = "2.5.2"
        const val retrofitVersion = "2.3.0"
        const val okhttpVersion = "3.9.1"
        const val koinVersion = "2.0.1"

        // Testing libs
        const val junitVersion = "4.12"
        const val androidxJunitVersion = "1.1.1"
        const val espressoCoreVersion = "3.2.0"
    }

    object Tools {
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradleVersion}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCoreVersion}"
    }

    object Android {
        const val buildToolsVersion = "29.0.2"
        const val minSdkVersion = 19
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val applicationId = "com.test.axontest"
        const val versionCode = 1
        const val versionName = "1.0"

        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
    }

    object ThirdPartyLibs {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val koinCore= "org.koin:koin-core:${Versions.koinVersion}"
    }

    object TestingLibs {
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunitVersion}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    }
}