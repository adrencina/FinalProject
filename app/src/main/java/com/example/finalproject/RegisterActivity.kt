package com.example.finalproject

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finalproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        validateEmail()
        validateInputs()
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateInputs(): Boolean {
        val email = binding.etEmail.text.toString()
        var isValid = true

        if (!isValidEmail(email)) {
            binding.tvErrorRegister.visibility = View.VISIBLE
            isValid = false
        } else {
            binding.tvErrorRegister.visibility = View.GONE
        }

        // Additional validation for other fields can go here

        return isValid
    }










//    private fun validateEmail(): Boolean {
//        val email = binding.etEmail.text.toString()
//        return if (email.isEmpty()) {
//            binding.etEmail.error = "Ingrese su Email"
//            false
//        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
//            binding.etEmail.error = "Email inválido"
//            false
//        } else {
//            binding.etEmail.error = null
//            true
//        }
//    }



}