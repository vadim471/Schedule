package com.example.schedule.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.data.usecase.GetScheduleForWeek

class WeekViewModelFactory(
    private val getScheduleForWeek: GetScheduleForWeek
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeekViewModel::class.java)) {
            return WeekViewModel(getScheduleForWeek) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}