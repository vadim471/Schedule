package com.example.schedule.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {
    @Volatile
    private var INSTANCE: ScheduleDatabase? = null
    private val databaseCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.let { database ->
                    val lessons = listOf(
                        LessonEntity(0, "Базы Данных (Лек)", "18:20", "19:50", "А-13", "Барабанщиков С.А.", "Понедельник"),
                        LessonEntity(0, "Базы Данных (Пр)", "19:55", "21:25", "А-13", "Барабанщиков С.А.", "Понедельник"),
                        LessonEntity(0, "Анализ данных (Пр)", "15:00", "16:30", "212", "Мирасов", "Вторник"),
                        LessonEntity(0, "Разработка интернет приложений (Лек)", "19:30", "19:50", "132", "Евгений Повлеченков", "Среда"),
                        LessonEntity(0, "Управление IT-проектами и ЖЦ ПО (Лек)", "18:20", "19:50", "А-21", "Владислав Воробьёв", "Четверг"),
                        LessonEntity(0, "Управление IT-проектами и ЖЦ ПО (Лек)", "19:55", "21:25", "А-21", "Владислав Воробьёв", "Четверг"),
                        LessonEntity(0, "Технологии прикладного программирования (Пр)", "16:40", "18:10", "326", "Никита Веригин", "Суббота"),
                        LessonEntity(0, "Технологии прикладного программирования (Пр)", "16:40", "18:10", "326", "Никита Веригин", "Суббота"),
                    )
                    database.scheduleDao().insertLessons(lessons)
                }
            }
        }
    }

    fun getDatabase(context: Context): ScheduleDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ScheduleDatabase::class.java,
                "schedule_database"
            ).addCallback(databaseCallback)
                .build()
            INSTANCE = instance
            instance
        }
    }
}