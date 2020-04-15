package com.test.axontest.face.domain.di

import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.di.fragment.FragmentScope
import com.test.axontest.face.data.repository.DetectedFaceRepositoryImpl
import com.test.axontest.face.domain.repository.DetectedFaceRepository
import com.test.axontest.face.domain.usecase.GetDetectedFaceUseCase
import com.test.axontest.face.domain.usecase.GetDetectedFaceUseCaseImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Inject

@Module
class DetectedFaceModule {
    @FragmentScope
    @Provides
    @Inject
    fun provideGetDetectedFaceUseCase(
        repository: DetectedFaceRepository,
        ioExecutor: Executor
    ): GetDetectedFaceUseCase = GetDetectedFaceUseCaseImpl(repository, ioExecutor)

    @FragmentScope
    @Provides
    @Inject
    fun provideDetectedFaceRepository(detectedFaceDao: DetectedFaceDao): DetectedFaceRepository =
        DetectedFaceRepositoryImpl(detectedFaceDao)
}