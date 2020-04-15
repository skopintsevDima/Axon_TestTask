package com.test.axontest.detector.domain.di

import android.content.Context
import android.util.Log
import com.test.axontest.R
import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.detector.domain.usecase.SaveDetectedFaceUseCase
import com.test.axontest.detector.domain.usecase.SaveDetectedFaceUseCaseImpl
import com.test.axontest.di.activity.ActivityScope
import com.test.axontest.detector.data.repository.DetectedFacesRepositoryImpl
import com.test.axontest.detector.domain.repository.DetectedFacesRepository
import dagger.Module
import dagger.Provides
import org.opencv.objdetect.CascadeClassifier
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.Executors
import javax.inject.Inject

@Module
class DetectorModule {
    @ActivityScope
    @Provides
    @Inject
    fun provideFaceDetector(context: Context): CascadeClassifier {
        context.run {
            try {
                val inputStream = resources.openRawResource(R.raw.lbpcascade_frontalface_improved)
                val cascadeDir = getDir(DIR_NAME_CASCADE, Context.MODE_PRIVATE)
                val cascadeFile = File(cascadeDir,
                    FILE_NAME_MODEL_CASCADE
                )
                val outputStream = FileOutputStream(cascadeFile)
                val buffer = ByteArray(4096)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                inputStream.close()
                outputStream.close()
                val faceDetector = CascadeClassifier(cascadeFile.absolutePath)
                if (faceDetector.empty()) {
                    Log.e(TAG, "Failed to load cascade classifier")
                } else {
                    Log.d(TAG, "Loaded cascade classifier from " + cascadeFile.absolutePath)
                }
                cascadeDir.delete()
                return faceDetector
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e(TAG, "Failed to load cascade. Exception thrown: $e")
                return CascadeClassifier()
            }
        }
    }

    @ActivityScope
    @Provides
    @Inject
    fun provideSaveDetectedFaceUseCase(repository: DetectedFacesRepository): SaveDetectedFaceUseCase =
        SaveDetectedFaceUseCaseImpl(repository, Executors.newSingleThreadExecutor())

    @ActivityScope
    @Provides
    @Inject
    fun provideDetectedFacesRepository(detectedFaceDao: DetectedFaceDao): DetectedFacesRepository =
        DetectedFacesRepositoryImpl(detectedFaceDao)

    companion object {
        const val TAG = "UtilsModule"
        const val DIR_NAME_CASCADE = "cascade"
        const val FILE_NAME_MODEL_CASCADE = "lbpcascade_frontalface_improved.xml"
    }
}