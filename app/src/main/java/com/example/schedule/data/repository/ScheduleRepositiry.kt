package com.example.schedule.data.repository

import com.example.schedule.data.db.ScheduleDao
import com.example.schedule.data.models.Lesson
import com.example.schedule.data.models.Schedule

class ScheduleRepository(private val scheduleDao: ScheduleDao) {

    suspend fun getLessonsForDay(dayName: String): List<Lesson> {
        return scheduleDao.getLessonsForDay(dayName).map { lesson ->
            Lesson(
                title = lesson.title,
                startTime = lesson.startTime,
                endTime = lesson.endTime,
                room = lesson.room,
                teacher = lesson.teacher
            )
        }
    }

    suspend fun getLessonsForWeek(): List<Schedule> {
        val lessons = scheduleDao.getLessonsForWeek()
        val dayOrder = listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота")
        return lessons.groupBy { it.dayName }.map { (dayName, lessonsForDay) ->
            Schedule(
                dayName = dayName,
                lessons = lessonsForDay.map { lesson ->
                    Lesson(
                        title = lesson.title,
                        startTime = lesson.startTime,
                        endTime = lesson.endTime,
                        room = lesson.room,
                        teacher = lesson.teacher
                    )
                }
            )
        }.sortedBy { schedule -> dayOrder.indexOf(schedule.dayName) }
    }
}