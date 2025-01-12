package com.example.schedule.ui.view.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.data.models.Schedule
import com.example.schedule.databinding.DayBinding

class WeekAdapter(
    var weekSchedule: List<Schedule>
) : RecyclerView.Adapter<WeekAdapter.DayViewHolder>() {

    inner class DayViewHolder(private val binding: DayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(daySchedule: Schedule) {
            binding.dayTitle.text = daySchedule.dayName
            val lessonAdapter = LessonAdapter(daySchedule.lessons)
            binding.dayLessonsRecyclerView.adapter = lessonAdapter
            binding.dayLessonsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = DayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(weekSchedule[position])
    }

    override fun getItemCount() = weekSchedule.size
}
