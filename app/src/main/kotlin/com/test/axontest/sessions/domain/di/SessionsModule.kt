package com.test.axontest.sessions.domain.di

import android.content.Context
import androidx.core.content.ContextCompat
import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.di.fragment.FragmentScope
import com.test.axontest.sessions.data.repository.UserSessionsRepositoryImpl
import com.test.axontest.sessions.data.source.SessionsDataSource
import com.test.axontest.sessions.domain.repository.UserSessionsRepository
import com.test.axontest.sessions.domain.usecase.GetUserSessionsUseCase
import com.test.axontest.sessions.domain.usecase.GetUserSessionsUseCaseImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Inject

@Module
class SessionsModule {
    @FragmentScope
    @Provides
    @Inject
    fun provideGetDetectedFaceUseCase(
        repository: UserSessionsRepository,
        ioExecutor: Executor
    ): GetUserSessionsUseCase = GetUserSessionsUseCaseImpl(repository, ioExecutor)

    @FragmentScope
    @Provides
    @Inject
    fun provideUserSessionsRepository(
        sessionsDataSource: SessionsDataSource,
        ioExecutor: Executor,
        context: Context
    ): UserSessionsRepository = UserSessionsRepositoryImpl(
        sessionsDataSource,
        ioExecutor,
        ContextCompat.getMainExecutor(context)
    )

    @FragmentScope
    @Provides
    @Inject
    fun provideSessionsDataSource(
        detectedFaceDao: DetectedFaceDao
    ) = SessionsDataSource(detectedFaceDao)
}