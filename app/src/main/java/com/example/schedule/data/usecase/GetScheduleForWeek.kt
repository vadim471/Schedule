package com.example.schedule.data.usecase

import com.example.schedule.data.models.Lesson
import com.example.schedule.data.models.Schedule
import com.example.schedule.data.repository.ScheduleRepository

class GetScheduleForWeek(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(): List<Schedule> {
        return repository.getLessonsForWeek()
    }
}