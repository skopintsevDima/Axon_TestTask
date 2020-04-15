package com.test.axontest.sessions.data.repository

import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.sessions.domain.model.UserSession
import com.test.axontest.sessions.domain.repository.UserSessionsRepository
import javax.inject.Inject

class UserSessionsRepositoryImpl @Inject constructor(
    private val detectedFaceDao: DetectedFaceDao
): UserSessionsRepository {
    override fun getAll(): List<UserSession> =
        detectedFaceDao.getAll()
            .map { UserSession(it.timestamp, it.photoUri) }
}