package com.example.schedule.data.usecase

import com.example.schedule.data.models.Lesson
import com.example.schedule.data.repository.ScheduleRepository

class GetScheduleForToday(private val repository: ScheduleRepository) {
    suspend operator fun invoke(dayName: String): List<Lesson> {
        return repository.getLessonsForDay(dayName)
    }
}