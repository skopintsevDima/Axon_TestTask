package com.test.axontest.sessions.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.test.axontest.sessions.domain.model.UserSession

interface GetUserSessionsUseCase {
    fun invoke(): LiveData<Result<PagedList<UserSession>>>
}