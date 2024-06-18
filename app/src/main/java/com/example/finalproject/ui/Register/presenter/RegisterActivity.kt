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
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.PreLoginActivity
import com.example.finalproject.R
import com.example.finalproject.ui.register.viewmodel.RegisterViewModel
import com.example.finalproject.Utils.enable
import com.example.finalproject.Utils.visible
import com.example.finalproject.data.repository.RegisterRepository
import com.example.finalproject.data.repository.TokenManager
import com.example.finalproject.data.service.RegisterApiServisImp
import com.example.finalproject.data.service.dto.RegisterState
import com.example.finalproject.databinding.ActivityRegisterBinding
import com.example.finalproject.ui.login.presenter.LoginActivity
import com.example.finalproject.ui.register.viewmodel.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

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

        val apiService = RegisterApiServisImp()
        val repository = RegisterRepository(apiService)
        registerViewModel = ViewModelProvider(this,
            RegisterViewModelFactory(repository)).get(RegisterViewModel::class.java)

        observeViewModel()
        setupListeners()

    }

    private fun setupListeners() {
        binding.cvInitRegister.enable(false)
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
            registerUser()
        }
        binding.etEmail.addTextChangedListener {
            registerViewModel.onEmailChanged(it.toString())
        }
        binding.etPassword.addTextChangedListener {
            registerViewModel.onPasswordChanged(it.toString())
        }
        binding.etConfirmPassword.addTextChangedListener {
            registerViewModel.onConfirmPasswordChanged(it.toString())
        }
        binding.tvBoldRegister.setOnClickListener {
            navigateToLogin()
        }
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
        registerViewModel.registerState.observe(this) { state ->
            when (state) {
                is RegisterState.Loading -> {
                    binding.containerRegister.visible(true)
                    binding.cvErrorRegister.visible(false)
                }

                is RegisterState.Success -> {
                    binding.containerRegister.visible(false)
                    state.accessToken?.let { TokenManager.saveAuthToken(this, it) }
                    navigateToHome()
                }

                is RegisterState.Error -> {
                    binding.containerRegister.visible(false)
                    binding.cvErrorRegister.visible(true)
                    binding.tvErrorRegister.text = state.message
                    binding.tvErrorRegister.visible(true)
                }

                RegisterState.Invalid -> {
                    updateButtonState(false)
                }

                RegisterState.Ready -> {
                    updateButtonState(true)
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, PreLoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun updateButtonState(allValid: Boolean) {
        binding.cvInitRegister.enable(allValid)
        binding.cvErrorRegister.visible(!allValid)
    }
}





