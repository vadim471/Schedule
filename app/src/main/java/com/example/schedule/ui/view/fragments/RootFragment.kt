package com.example.schedule.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedule.R
import com.example.schedule.databinding.RootFragmentBinding

class RootFragment : Fragment() {
    private var _binding: RootFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RootFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
           when (item.itemId) {
                R.id.page_week -> {
                    loadFragment(WeekFragment())
                    true
                }
                R.id.page_today -> {
                    loadFragment(TodayFragment())
                    true
                }
               else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.rootFragmentContainer, fragment)
            .commit()
    }
}