package com.test.axontest.sessions.data.repository

import androidx.paging.PagedList
import com.test.axontest.sessions.data.source.SessionsDataSource
import com.test.axontest.sessions.data.source.SessionsDataSource.Companion.MAX_SIZE
import com.test.axontest.sessions.data.source.SessionsDataSource.Companion.PAGE_SIZE
import com.test.axontest.sessions.domain.model.UserSession
import com.test.axontest.sessions.domain.repository.UserSessionsRepository
import java.util.concurrent.Executor

class UserSessionsRepositoryImpl(
    private val sessionsDataSource: SessionsDataSource,
    private val ioExecutor: Executor,
    private val mainExecutor: Executor
): UserSessionsRepository {
    override fun getAll(): PagedList<UserSession> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setMaxSize(MAX_SIZE)
            .setEnablePlaceholders(false)
            .build()
        return PagedList.Builder(sessionsDataSource, config)
            .setFetchExecutor(ioExecutor)
            .setNotifyExecutor(mainExecutor)
            .build()
    }
}