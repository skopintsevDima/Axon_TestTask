package com.test.axontest.detector.data.repository

import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.detector.domain.model.DetectedFace
import com.test.axontest.detector.domain.repository.DetectedFacesRepository

class DetectedFacesRepositoryImpl(
    private val detectedFaceDao: DetectedFaceDao
): DetectedFacesRepository {
    override fun insert(detectedFace: DetectedFace): Long = detectedFaceDao.insert(detectedFace)
}