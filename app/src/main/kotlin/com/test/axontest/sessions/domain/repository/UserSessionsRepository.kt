package com.test.axontest.sessions.domain.repository

import com.test.axontest.sessions.domain.model.UserSession

interface UserSessionsRepository {
    fun getAll(): List<UserSession>
}