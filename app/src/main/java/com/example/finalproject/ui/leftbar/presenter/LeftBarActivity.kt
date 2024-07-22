package com.example.finalproject.ui.leftbar.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.example.finalproject.ui.descriptionFragment.presenter.DescriptionFragment

class LeftBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeftBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}