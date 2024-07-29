package com.example.finalproject.ui.login.presenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.TokenManager
import com.example.finalproject.data.service.dto.LoginState
import com.example.finalproject.data.service.dto.Utils.visible
import com.example.finalproject.databinding.ActivityLoginBinding
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.register.presenter.RegisterActivity
import com.example.finalproject.ui.login.viewmodel.LoginViewModel
import com.example.finalproject.ui.login.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(application)
        )[LoginViewModel::class.java]


//         Verifica si ya hay un token almacenado
        val token = TokenManager.getToken(this)
        Log.i("DATA-Login",token)
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
                    onErrorManager()
                }

                else -> {
                    stopLoading()
                    showToast("Error desconocido")
                    onErrorManager()
                }
            }
        }

        // listeners botones
        binding.checkBoxPassword.setOnClickListener {
            checkBoxSwitcher()
        }
        binding.cvLogIn.setOnClickListener { login() }
        binding.btnGoToRegister.setOnClickListener { navigateToRegister() }
        initInputListeners()
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

    private fun checkBoxSwitcher(){
        val passwordBox = binding.inputPassword
        val checkBox = binding.checkBoxPassword
        loginViewModel.checkBoxChecker(checkBox,passwordBox)
    }
    private fun onErrorManager(){
        val errorTextBox = binding.emailErrorMessage
        val btnOK = binding.cvLogIn
        val btnError = binding.cvLogInError
        loginViewModel.btnSwitcher(errorTextBox,btnOK,btnError)
    }

    private fun initInputListeners(){
        inputEmailChangeListener()
        inputPasswordChangeListener()
    }
    private fun inputEmailChangeListener(){
        val emailBox = binding.inputEmail
        val btnOK = binding.cvLogIn
        val btnError = binding.cvLogInError
        val errorTextBox = binding.emailErrorMessage
        loginViewModel.emailListenerChange(errorTextBox,emailBox, btnOK, btnError)
    }
    private fun inputPasswordChangeListener(){
        val passwordBox = binding.inputPassword
        val btnOK = binding.cvLogIn
        val btnError = binding.cvLogInError
        val errorTextBox = binding.emailErrorMessage
        loginViewModel.passwordListenerChange(errorTextBox, passwordBox, btnOK, btnError)
    }
}