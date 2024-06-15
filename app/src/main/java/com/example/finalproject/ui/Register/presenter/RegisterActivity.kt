package com.example.finalproject.ui.register.presenter

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.finalproject.R
import com.example.finalproject.ui.register.viewmodel.RegisterViewModel
import com.example.finalproject.Utils.enable
import com.example.finalproject.Utils.visible
import com.example.finalproject.data.service.dto.RegisterState
import com.example.finalproject.databinding.ActivityRegisterBinding
import com.example.finalproject.ui.login.presenter.LoginActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel>()

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

        observeViewModel()

    }

    private fun validateInputs() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        registerViewModel.validateInputs(email, password, confirmPassword)
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
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        registerViewModel.registerUser(email, password)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            registerViewModel.registerState.collectLatest { state ->
                when (state) {
                    is RegisterState.Success -> {
                        binding.containerLoading.visible(false)
                        navigateToLogin()
                    }
                    is RegisterState.Error -> {
                        binding.containerLoading.visible(false)
                        binding.cvErrorRegister.visible(true)
                        handleErrorState(state)
                    }
                    is RegisterState.Validation -> {
                        handleValidationState(state)
                    }
                    RegisterState.Loading -> {
                        binding.containerLoading.visible(true)
                        binding.cvErrorRegister.visible(false)
                    }
                    RegisterState.Idle -> {
                        updateButtonState(false)
                    }
                    RegisterState.Ready -> {
                        updateButtonState(true)
                    }
                }
            }
        }
    }

    private fun handleValidationState(validationState: RegisterState.Validation) {
        val emailError = validationState.emailError
        val passwordError = validationState.passwordError
        binding.etEmail.error = emailError
        binding.etPassword.error = passwordError
        binding.tvErrorRegister.text = emailError ?: passwordError
        binding.tvErrorRegister.visible(emailError != null || passwordError != null)
    }
    private fun handleErrorState(errorState: RegisterState.Error) {
        val errorMessage = errorState.message ?: "Error de registro desconocido"
        binding.tvErrorRegister.text = errorMessage
        binding.tvErrorRegister.visible(true)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}





