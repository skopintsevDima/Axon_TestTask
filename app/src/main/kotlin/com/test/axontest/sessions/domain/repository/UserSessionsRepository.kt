package com.test.axontest.sessions.domain.repository

import androidx.paging.PagedList
import com.test.axontest.sessions.domain.model.UserSession

interface UserSessionsRepository {
    fun getAll(): PagedList<UserSession>
}