package com.test.axontest.sessions.domain.model

data class UserSession(
    val id: Long,
    val timestamp: Long,
    val photoUri: String
)