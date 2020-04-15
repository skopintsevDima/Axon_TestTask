package com.test.axontest.sessions.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.test.axontest.sessions.domain.model.UserSession
import com.test.axontest.sessions.domain.repository.UserSessionsRepository
import java.util.concurrent.Executor

class GetUserSessionsUseCaseImpl(
    private val userSessionsRepository: UserSessionsRepository,
    private val executor: Executor
): GetUserSessionsUseCase {
    override fun invoke(): LiveData<Result<PagedList<UserSession>>> {
        val result = MutableLiveData<Result<PagedList<UserSession>>>()
        executor.execute {
            try {
                val userSessions = userSessionsRepository.getAll()
                result.postValue(Result.success(userSessions))
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(Result.failure(e))
            }
        }
        return result
    }
}