package com.test.axontest.sessions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.test.axontest.sessions.domain.model.UserSession
import com.test.axontest.sessions.domain.usecase.GetUserSessionsUseCase
import javax.inject.Inject

class SessionsViewModel @Inject constructor(
    private val getUserSessionsUseCase: GetUserSessionsUseCase
): ViewModel() {
    fun loadSessions(): LiveData<Result<PagedList<UserSession>>> = getUserSessionsUseCase.invoke()
}