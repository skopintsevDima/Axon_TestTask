package com.test.axontest.sessions.data.source

import androidx.paging.PositionalDataSource
import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.sessions.domain.model.UserSession

class SessionsDataSource(
    private val detectedFaceDao: DetectedFaceDao
): PositionalDataSource<UserSession>() {
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<UserSession>
    ) {
        val userSessions = detectedFaceDao.get(PAGE_SIZE.toLong(), 0)
            .map { UserSession(it.id, it.timestamp, it.photoUri) }
        callback.onResult(userSessions, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<UserSession>) {
        val userSessions = detectedFaceDao.get(PAGE_SIZE.toLong(), params.startPosition.toLong())
            .map { UserSession(it.id, it.timestamp, it.photoUri) }
        callback.onResult(userSessions)
    }

    companion object {
        const val PAGE_SIZE = 10
        const val MAX_SIZE = 100
    }
}