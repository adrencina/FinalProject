package com.example.finalproject.ui.leftbar.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityLeftBarBinding
class LeftBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeftBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}