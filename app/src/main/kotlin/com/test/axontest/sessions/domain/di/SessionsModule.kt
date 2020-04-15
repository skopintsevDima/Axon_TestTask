package com.test.axontest.sessions.domain.di

import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.di.fragment.FragmentScope
import com.test.axontest.sessions.data.repository.UserSessionsRepositoryImpl
import com.test.axontest.sessions.domain.repository.UserSessionsRepository
import com.test.axontest.sessions.domain.usecase.GetUserSessionsUseCase
import com.test.axontest.sessions.domain.usecase.GetUserSessionsUseCaseImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Inject

@Module
class SessionsModule {
    @FragmentScope
    @Provides
    @Inject
    fun provideGetDetectedFaceUseCase(repository: UserSessionsRepository): GetUserSessionsUseCase =
        GetUserSessionsUseCaseImpl(repository, Executors.newSingleThreadExecutor())

    @FragmentScope
    @Provides
    @Inject
    fun provideUserSessionsRepository(detectedFaceDao: DetectedFaceDao): UserSessionsRepository =
        UserSessionsRepositoryImpl(detectedFaceDao)
}