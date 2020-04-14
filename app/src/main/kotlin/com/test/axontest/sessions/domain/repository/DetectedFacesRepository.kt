package com.test.axontest.sessions.domain.repository

import com.test.axontest.sessions.domain.model.DetectedFace

interface DetectedFacesRepository {
    fun getAll(): List<DetectedFace>

    fun insert(detectedFace: DetectedFace): Long
}