package com.test.axontest.db.dao

import androidx.room.*
import com.test.axontest.detector.domain.model.DetectedFace

@Dao
interface DetectedFaceDao {
    @Query("SELECT * FROM detectedface LIMIT (:count) OFFSET (:position)")
    fun get(count: Long, position: Long): List<DetectedFace>

    @Query("SELECT * FROM detectedface WHERE id = (:detectedFaceId)")
    fun getById(detectedFaceId: Long): DetectedFace

    @Insert
    fun insert(detectedFace: DetectedFace): Long
}