package com.example.schedule.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val startTime: String,
    val endTime: String,
    val room: String,
    val teacher: String,
    val dayName: String
)
