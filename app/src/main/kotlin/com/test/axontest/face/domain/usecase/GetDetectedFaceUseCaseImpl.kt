package com.test.axontest.face.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.axontest.face.domain.repository.DetectedFaceRepository
import com.test.axontest.detector.domain.model.DetectedFace
import java.util.concurrent.Executor

class GetDetectedFaceUseCaseImpl(
    private val detectedFaceRepository: DetectedFaceRepository,
    private val executor: Executor
): GetDetectedFaceUseCase {
    override fun invoke(detectedFaceId: Long): LiveData<Result<DetectedFace>> {
        val result = MutableLiveData<Result<DetectedFace>>()
        executor.execute {
            try {
                val detectedFace = detectedFaceRepository.getById(detectedFaceId)
                result.postValue(Result.success(detectedFace))
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(Result.failure(e))
            }
        }
        return result
    }
}