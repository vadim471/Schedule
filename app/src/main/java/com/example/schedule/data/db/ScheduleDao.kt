package com.example.schedule.data.db

import androidx.room.*


@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)

    @Query("SELECT * FROM lessons WHERE dayName = :dayName ORDER BY startTime ASC")
    suspend fun getLessonsForDay(dayName: String): List<LessonEntity>

    @Query("SELECT * FROM lessons ORDER BY startTime ASC")
    suspend fun getLessonsForWeek(): List<LessonEntity>
}
