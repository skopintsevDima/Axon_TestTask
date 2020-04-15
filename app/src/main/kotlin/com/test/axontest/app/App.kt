package com.test.axontest.app

import android.app.Application
import android.util.Log
import com.test.axontest.app.di.AppComponent
import com.test.axontest.app.di.AppModule
import com.test.axontest.app.di.DaggerAppComponent
import com.test.axontest.db.di.DbModule
import org.opencv.android.InstallCallbackInterface
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initOpenCV()
        buildAppComponent()
    }

    private fun initOpenCV() {
        val wasEngineInitialized = OpenCVLoader.initDebug()
        if (wasEngineInitialized){
            Log.d(TAG, "The OpenCV was successfully initialized in debug mode.")
        } else {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, object : LoaderCallbackInterface {
                override fun onManagerConnected(status: Int) {
                    when (status) {
                        LoaderCallbackInterface.SUCCESS -> Log.d(TAG, "OpenCV successfully started.")
                        LoaderCallbackInterface.INIT_FAILED -> Log.d(TAG, "Failed to start OpenCV.")
                        LoaderCallbackInterface.MARKET_ERROR -> Log.d(TAG, "Google Play Store could not be invoked. Please check if you have the Google Play Store app installed and try again.")
                        LoaderCallbackInterface.INSTALL_CANCELED -> Log.d(TAG, "OpenCV installation has been cancelled by the user.")
                        LoaderCallbackInterface.INCOMPATIBLE_MANAGER_VERSION -> Log.d(TAG, "This version of OpenCV Manager is incompatible. Possibly, a service update is required.")
                    }
                }

                override fun onPackageInstall(
                    operation: Int,
                    callback: InstallCallbackInterface?
                ) {
                    Log.d(TAG, "OpenCV Manager successfully installed from Google Play.")
                }
            })
        }
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dbModule(DbModule())
            .build()
    }

    companion object {
        const val TAG = "App"
        lateinit var appComponent: AppComponent
    }
}