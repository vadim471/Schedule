package com.example.schedule.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LessonEntity::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}