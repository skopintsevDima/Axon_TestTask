package com.test.axontest.face.domain.usecase

import androidx.lifecycle.LiveData
import com.test.axontest.detector.domain.model.DetectedFace

interface GetDetectedFaceUseCase {
    fun invoke(detectedFaceId: Long): LiveData<Result<DetectedFace>>
}