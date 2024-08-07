package com.example.finalproject

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.ui.preLogin.presenter.PreLoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      navigateToPreLogin()
    }
    private fun navigateToPreLogin(){
        val intent = Intent(this, PreLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}