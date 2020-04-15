package com.test.axontest.sessions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.axontest.sessions.domain.model.UserSession
import com.test.axontest.sessions.domain.usecase.GetUserSessionsUseCase
import javax.inject.Inject

class SessionsViewModel @Inject constructor(
    private val getUserSessionsUseCase: GetUserSessionsUseCase
): ViewModel() {
    fun loadSessions(): LiveData<Result<List<UserSession>>> = getUserSessionsUseCase.invoke()
}