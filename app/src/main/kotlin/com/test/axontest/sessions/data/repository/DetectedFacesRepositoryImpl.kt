package com.test.axontest.sessions.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.sessions.domain.model.DetectedFace
import com.test.axontest.sessions.domain.repository.DetectedFacesRepository
import javax.inject.Inject

class DetectedFacesRepositoryImpl @Inject constructor(
    private val detectedFaceDao: DetectedFaceDao
): DetectedFacesRepository {
    override fun getAll(): List<DetectedFace> = detectedFaceDao.getAll()

    override fun insert(detectedFace: DetectedFace): Long = detectedFaceDao.insert(detectedFace)
}