package com.test.axontest.detector.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetectedFace(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val top: Int,
    val left: Int,
    val width: Int,
    val height: Int,
    val timestamp: Long,
    val photoUri: String
)