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
import com.example.schedule.data.usecase.GetScheduleForWeek
import com.example.schedule.databinding.WeekFragmentBinding
import com.example.schedule.ui.view.fragments.adapters.WeekAdapter
import com.example.schedule.ui.viewmodel.WeekViewModel
import com.example.schedule.ui.viewmodel.WeekViewModelFactory

class WeekFragment : Fragment() {

    private var _binding: WeekFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WeekViewModel

    private val getScheduleForWeek: GetScheduleForWeek by lazy {
        val scheduleDao = DatabaseProvider.getDatabase(requireContext()).scheduleDao()

        val repository = ScheduleRepository(scheduleDao)

        GetScheduleForWeek(repository)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeekFragmentBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = WeekViewModelFactory(getScheduleForWeek)
        viewModel = ViewModelProvider(this, factory)[WeekViewModel::class.java]

        observeViewModel()

        viewModel.loadScheduleForWeek()
    }

    private fun observeViewModel() {
        viewModel.schedule.observe(viewLifecycleOwner) { schedule ->
            (binding.recyclerView.adapter as WeekAdapter).apply {
                this.weekSchedule = schedule
                notifyDataSetChanged()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = WeekAdapter(emptyList())
    }


}