package com.example.schedule.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.data.models.Lesson
import com.example.schedule.data.usecase.GetScheduleForToday
import kotlinx.coroutines.launch

class TodayViewModel(
    private val getLessonsForDayUseCase: GetScheduleForToday
) : ViewModel() {

    private val _lessons = MutableLiveData<List<Lesson>>()
    val lessons: LiveData<List<Lesson>> get() = _lessons

    fun loadLessonsForToday(dayName: String) {
        viewModelScope.launch {
            val lessons = getLessonsForDayUseCase(dayName)
            _lessons.postValue(lessons)
        }
    }
}