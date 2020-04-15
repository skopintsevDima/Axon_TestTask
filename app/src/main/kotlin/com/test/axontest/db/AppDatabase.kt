package com.test.axontest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.axontest.db.AppDatabase.Companion.APP_DATABASE_VERSION
import com.test.axontest.db.dao.DetectedFaceDao
import com.test.axontest.detector.domain.model.DetectedFace

@Database(entities = [DetectedFace::class], version = APP_DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun detectedFaceDao(): DetectedFaceDao

    companion object {
        const val APP_DATABASE_VERSION = 1
        const val APP_DATABASE_NAME = "Test.db"
    }
}