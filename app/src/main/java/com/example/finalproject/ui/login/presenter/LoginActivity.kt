package com.example.finalproject.ui.login.presenter

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finalproject.data.dto.response.LoginResponse
import com.example.finalproject.data.repository.TokenManager
import com.example.finalproject.data.service.dto.LoginState
import com.example.finalproject.R
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
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val token = TokenManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        loginViewModel.loginResult.observe(this) {
            when (it) {
                is LoginState.Loading -> {
                    showLoading()
                }
                is LoginState.Success -> {
                    stopLoading()
                    processLogin(it.data)
                    navigateToHome()
                }
                is LoginState.Error -> {
                    processError(it.msg)
                    stopLoading()
                    onErrorManager()
                }
                else -> {
                    stopLoading()
                    onErrorManager()
                }
            }
        }
        binding.cvLogIn.setOnClickListener {
            login()
        }
        binding.btnGoToRegister.setOnClickListener {
            navigateToRegister()
        }
        binding.checkBoxPassword.setOnClickListener {
            checkBoxSwitcher()
        }
        initInputListeners()
    }
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
    private fun login() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        loginViewModel.passwordRegexValidation(password = password)
        loginViewModel.passwordChecker()
        loginViewModel.loginUser(email = email, password = password)
    }
    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
    private fun showLoading() {
        binding.containerLoading.visible(true)
    }
    private fun stopLoading() {
        binding.containerLoading.visible(false)
    }
    private fun processLogin(data: LoginResponse?) {
        showToast("Success:" +" "+ TokenManager.getToken(this))
        if (!data?.data?.accessToken.isNullOrEmpty()) {
            data?.data?.accessToken?.let { TokenManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }
    private fun processError(msg: String?) {
        showToast("Error:$msg")
    }
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
    private fun onErrorManager(){
        val errorTextBox = binding.emailErrorMessage
        val btnOK = binding.cvLogIn
        val btnError = binding.cvLogInError
        loginViewModel.btnSwitcher(errorTextBox,btnOK,btnError)
    }
    private fun checkBoxSwitcher(){
        val passwordBox = binding.inputPassword
        val checkBox = binding.checkBoxPassword
        loginViewModel.checkBoxChecker(checkBox,passwordBox)
    }
}


