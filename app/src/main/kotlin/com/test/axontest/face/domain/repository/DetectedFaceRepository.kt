package com.test.axontest.face.domain.repository

import com.test.axontest.sessions.domain.model.DetectedFace

interface DetectedFaceRepository {
    fun getById(detectedFaceId: Long): DetectedFace
}