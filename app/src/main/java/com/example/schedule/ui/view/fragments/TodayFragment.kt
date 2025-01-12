package com.example.schedule.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.data.db.DatabaseProvider
import com.example.schedule.data.repository.ScheduleRepository
import com.example.schedule.data.usecase.GetScheduleForToday
import com.example.schedule.databinding.TodayFragmentBinding
import com.example.schedule.ui.view.fragments.adapters.LessonAdapter
import com.example.schedule.ui.viewmodel.TodayViewModel
import com.example.schedule.ui.viewmodel.TodayViewModelFactory

class TodayFragment : Fragment() {

    private var _binding: TodayFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TodayViewModel

    private val getScheduleForToday: GetScheduleForToday by lazy {
        val scheduleDao = DatabaseProvider.getDatabase(requireContext()).scheduleDao()

        val repository = ScheduleRepository(scheduleDao)

        GetScheduleForToday(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodayFragmentBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = TodayViewModelFactory(getScheduleForToday)
        viewModel = ViewModelProvider(this, factory)[TodayViewModel::class.java]

        observeViewModel()
        val today = "Понедельник"
        viewModel.loadLessonsForToday(today)
    }

    private fun observeViewModel() {
        viewModel.lessons.observe(viewLifecycleOwner) { lessons ->
            (binding.recyclerView.adapter as LessonAdapter).apply {
                this.lessons = lessons
                notifyDataSetChanged()
            }
        }
    }
    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = LessonAdapter(emptyList())
    }
}
