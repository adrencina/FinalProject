package com.example.finalproject
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityPreLoginBinding


class PreLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}