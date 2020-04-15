package com.test.axontest.sessions.domain.usecase

import androidx.lifecycle.LiveData
import com.test.axontest.sessions.domain.model.UserSession

interface GetUserSessionsUseCase {
    fun invoke(): LiveData<Result<List<UserSession>>>
}