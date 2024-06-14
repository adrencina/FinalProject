package com.example.finalproject.ui.Register

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.finalproject.R
import com.example.finalproject.Utils
import com.example.finalproject.Utils.enable
import com.example.finalproject.Utils.visible
import com.example.finalproject.databinding.ActivityRegisterBinding
import com.example.finalproject.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val registerViewModel by viewModels<RegisterViewModel>()

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

        binding.cvInitRegister.enable(false)
        binding.cvErrorRegister.enable(true)

        binding.cbShowPassword.setOnClickListener {
            togglePasswordVisibility(binding.cbShowPassword.isChecked, binding.etPassword)
        }

        binding.cbShowConfPassword.setOnClickListener {
            togglePasswordVisibility(
                binding.cbShowConfPassword.isChecked,
                binding.etConfirmPassword
            )
        }

        binding.cvInitRegister.setOnClickListener {
            validateInputs()
            if (binding.cvInitRegister.isEnabled) registerUser()
        }

        binding.etEmail.addTextChangedListener {
            validateInputs()
        }
        binding.etPassword.addTextChangedListener {
            validateInputs()
        }
        binding.etConfirmPassword.addTextChangedListener {
            validateInputs()
        }

        binding.tvBoldRegister.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun validateInputs() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        val emailValid = validateEmail(email)
        val passwordValid = validatePassword(password)
        val passwordsMatch = password == confirmPassword

        val allValid = emailValid && passwordValid && passwordsMatch
        updateButtonState(allValid)
    }

    private fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.etEmail.error = "Email requerido."
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Email inválido."
            false
        } else {
            binding.etEmail.error = null
            true
        }
    }

    private fun validatePassword(password: String): Boolean {
        val isValid = when {
            password.length < 8 -> "Contraseña débil, es necesario mínimo 8 caracteres."
            !Utils.PASSWORD_UPPERCASE_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 letra mayúscula."

            !Utils.PASSWORD_NUMBER_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 número."

            !Utils.PASSWORD_LOWERCASE_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 letra minúscula."

            !Utils.PASSWORD_SPECIAL_CHARACTER_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 caracter especial."

            else -> null
        }

        binding.etPassword.error = isValid
        return isValid == null
    }

    private fun updateButtonState(allValid: Boolean) {
        binding.cvInitRegister.enable(allValid)
        binding.cvErrorRegister.visible(!allValid)
    }

    private fun togglePasswordVisibility(show: Boolean, passwordField: EditText) {
        passwordField.transformationMethod = if (show) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    private fun registerUser() {
        binding.containerLoading.visible(true)

        // Lógica de registro del usuario...

    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}





