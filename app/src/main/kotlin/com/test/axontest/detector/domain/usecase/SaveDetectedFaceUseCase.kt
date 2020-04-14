package com.test.axontest.detector.domain.usecase

import android.net.Uri
import androidx.lifecycle.LiveData
import com.test.axontest.detector.domain.util.DetectedFaceData

interface SaveDetectedFaceUseCase {
    fun invoke(detectedFaceData: DetectedFaceData, facePhotoUri: Uri): LiveData<Result<Long>>
}