package com.test.axontest.face.domain.repository

import com.test.axontest.detector.domain.model.DetectedFace

interface DetectedFaceRepository {
    fun getById(detectedFaceId: Long): DetectedFace
}