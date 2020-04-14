package com.test.axontest.detector.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.axontest.R
import com.test.axontest.detector.domain.usecase.SaveDetectedFaceUseCase
import com.test.axontest.detector.domain.util.DetectedFaceData
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetectorViewModel @Inject constructor(
    private val context: Context,
    private val saveDetectedFaceUseCase: SaveDetectedFaceUseCase
): ViewModel() {
    private lateinit var photoFile: File

    fun buildPhotoFileOutputOptions(photoTimestamp: Long): ImageCapture.OutputFileOptions {
        val photoFile = createPhotoFile(getOutputDirectory(context), photoTimestamp)
        val photoMetadata = ImageCapture.Metadata()
        return ImageCapture.OutputFileOptions.Builder(photoFile)
            .setMetadata(photoMetadata)
            .build()
            .also { this.photoFile = photoFile }
    }

    fun saveDetectedFace(
        detectedFaceData: DetectedFaceData,
        output: ImageCapture.OutputFileResults
    ): LiveData<Result<Long>> {
        val facePhotoUri = output.savedUri ?: Uri.fromFile(photoFile)
        Log.d(TAG, "Photo capture succeeded: $facePhotoUri")
        return saveDetectedFaceUseCase.invoke(detectedFaceData, facePhotoUri)
    }

    companion object {
        private const val TAG = "DetectorViewModel"
        private const val FILENAME_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss"
        private const val PHOTO_EXTENSION = ".jpg"

        private fun createPhotoFile(baseFolder: File, fileTimestamp: Long) =
            File(
                baseFolder,
                SimpleDateFormat(FILENAME_DATE_FORMAT, Locale.getDefault()).format(fileTimestamp) + PHOTO_EXTENSION
            )

        private fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() } }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }
}