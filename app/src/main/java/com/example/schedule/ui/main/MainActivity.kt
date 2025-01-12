package com.example.schedule.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedule.R
import com.example.schedule.ui.view.fragments.RootFragment
import com.example.schedule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction()
            .replace(R.id.main, RootFragment())
            .commit()
    }
}