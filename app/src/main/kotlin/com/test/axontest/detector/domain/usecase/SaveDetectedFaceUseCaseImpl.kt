package com.test.axontest.detector.domain.usecase

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.axontest.detector.domain.util.DetectedFaceData
import com.test.axontest.detector.domain.model.DetectedFace
import com.test.axontest.detector.domain.repository.DetectedFacesRepository
import java.util.concurrent.Executor
import javax.inject.Inject

class SaveDetectedFaceUseCaseImpl @Inject constructor(
    private val detectedFacesRepository: DetectedFacesRepository,
    private val executor: Executor
) : SaveDetectedFaceUseCase {
    override fun invoke(
        detectedFaceData: DetectedFaceData,
        facePhotoUri: Uri
    ): LiveData<Result<Long>> {
        val result = MutableLiveData<Result<Long>>()
        val detectedFace = DetectedFace(
            top = detectedFaceData.face.y,
            left = detectedFaceData.face.x,
            width = detectedFaceData.face.width,
            height = detectedFaceData.face.height,
            timestamp = detectedFaceData.timestamp,
            photoUri = facePhotoUri.toString()
        )
        executor.execute {
            try {
                val insertResult = detectedFacesRepository.insert(detectedFace)
                result.postValue(Result.success(insertResult))
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(Result.failure(e))
            }
        }
        return result
    }
}