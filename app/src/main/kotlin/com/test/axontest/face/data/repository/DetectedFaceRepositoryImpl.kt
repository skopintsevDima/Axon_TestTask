package com.test.axontest.face.data.repository

import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.face.domain.repository.DetectedFaceRepository
import com.test.axontest.detector.domain.model.DetectedFace
import javax.inject.Inject

class DetectedFaceRepositoryImpl @Inject constructor(
    private val detectedFaceDao: DetectedFaceDao
): DetectedFaceRepository {
    override fun getById(detectedFaceId: Long): DetectedFace =
        detectedFaceDao.getById(detectedFaceId)
}