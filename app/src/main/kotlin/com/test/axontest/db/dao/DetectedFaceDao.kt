package com.test.axontest.db.dao

import androidx.room.*
import com.test.axontest.sessions.domain.model.DetectedFace

@Dao
interface DetectedFaceDao {
    @Query("SELECT * FROM detectedface")
    fun getAll(): List<DetectedFace>

    @Query("SELECT * FROM detectedface WHERE id = (:detectedFaceId)")
    fun getById(detectedFaceId: Long): DetectedFace

    @Insert
    fun insert(detectedFace: DetectedFace): Long
}