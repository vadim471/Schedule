package com.example.schedule.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.data.repository.ScheduleRepository
import com.example.schedule.data.usecase.GetScheduleForToday

class TodayViewModelFactory(
    private val getScheduleForToday: GetScheduleForToday
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodayViewModel::class.java)) {
            return TodayViewModel(getScheduleForToday) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
