package com.example.finalproject.ui.preLogin.presenter

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityPreLoginBinding
import com.example.finalproject.ui.Register.RegisterActivity
import com.example.finalproject.ui.login.LoginActivity

class PreLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPreLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvInitLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }
        binding.cvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}