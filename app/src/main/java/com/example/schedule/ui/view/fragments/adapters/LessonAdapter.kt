package com.example.schedule.ui.view.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.data.models.Lesson
import com.example.schedule.databinding.LessonBinding

class LessonAdapter(
    var lessons: List<Lesson>
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(private val binding: LessonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            binding.lessonName.text = lesson.title
            binding.lessonTime.text = "${lesson.startTime} - ${lesson.endTime}"
            binding.lessonRoom.text = "Аудитория: ${lesson.room}"
            binding.lessonTeacher.text = "Преподаватель: ${lesson.teacher}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = LessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(lessons[position])
    }

    override fun getItemCount() = lessons.size
}
