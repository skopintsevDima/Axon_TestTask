package com.test.axontest.detector.domain.repository

import com.test.axontest.detector.domain.model.DetectedFace

interface DetectedFacesRepository {
    fun insert(detectedFace: DetectedFace): Long
}