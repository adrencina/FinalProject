package com.example.finalproject.ui.login.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.data.repository.TokenManager
import com.example.finalproject.data.service.dto.LoginState
import com.example.finalproject.Utils.visible
import com.example.finalproject.databinding.ActivityLoginBinding
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.register.presenter.RegisterActivity
import com.example.finalproject.ui.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verifica si ya hay un token almacenado
        val token = TokenManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        // Observa los resultados del login
        loginViewModel.loginResult.observe(this) { loginState ->
            when (loginState) {
                is LoginState.Loading -> showLoading()
                is LoginState.Success -> {
                    stopLoading()
                    navigateToHome()
                }

                is LoginState.Error -> {
                    stopLoading()
                    showToast(loginState.msg ?: "Error desconocido")
                }

                else -> {
                    stopLoading()
                    showToast("Error desconocido")
                }
            }
        }

        // listeners botones
        binding.cvLogIn.setOnClickListener { login() }
        binding.btnGoToRegister.setOnClickListener { navigateToRegister() }
    }

    // Fun nav a HomeActivity
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    // Fun iniciar sesión
    private fun login() {
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()
        loginViewModel.loginUser(email = email, password = password)
    }

    // Fun nav a RegisterActivity
    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    // Fun mostrar y ocultar el loading
    private fun showLoading() {
        binding.containerLoading.visible(true)
    }

    private fun stopLoading() {
        binding.containerLoading.visible(false)
    }

    // Fun mostrar un Toast
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}