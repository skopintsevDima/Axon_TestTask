package com.test.axontest.db.di

import android.content.Context
import androidx.room.Room
import com.test.axontest.db.AppDatabase
import com.test.axontest.db.AppDatabase.Companion.APP_DATABASE_NAME
import com.test.axontest.db.dao.DetectedFaceDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    @Inject
    fun provideDetectedFaceDao(appDatabase: AppDatabase): DetectedFaceDao = appDatabase.detectedFaceDao()

    @Provides
    @Singleton
    @Inject
    fun provideAppDatabase(applicationContext: Context): AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, APP_DATABASE_NAME
    ).build()
}