package com.example.schedule.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.data.models.Schedule
import com.example.schedule.data.usecase.GetScheduleForWeek
import kotlinx.coroutines.launch

class WeekViewModel (
    private val getScheduleForWeekUseCase: GetScheduleForWeek
)  : ViewModel() {

    private val _schedule = MutableLiveData<List<Schedule>>()
    val schedule: LiveData<List<Schedule>> get() = _schedule

    fun loadScheduleForWeek() {
        viewModelScope.launch {
            val schedule = getScheduleForWeekUseCase()
            _schedule.postValue(schedule)
        }
    }

}