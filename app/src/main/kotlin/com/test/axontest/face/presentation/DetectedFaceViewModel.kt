package com.test.axontest.face.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.axontest.face.domain.usecase.GetDetectedFaceUseCase
import com.test.axontest.sessions.domain.model.DetectedFace
import javax.inject.Inject

class DetectedFaceViewModel @Inject constructor(
    private val getDetectedFaceUseCase: GetDetectedFaceUseCase
) : ViewModel() {
    fun getDetectedFace(detectedFaceId: Long): LiveData<Result<DetectedFace>> =
        getDetectedFaceUseCase.invoke(detectedFaceId)
}