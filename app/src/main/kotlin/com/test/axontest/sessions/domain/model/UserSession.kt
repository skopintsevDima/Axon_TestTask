package com.test.axontest.sessions.domain.model

data class UserSession(
    private val timestamp: Long,
    private val photoUri: String
)