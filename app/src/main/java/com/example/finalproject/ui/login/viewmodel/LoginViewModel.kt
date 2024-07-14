package com.example.finalproject.ui.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.request.LoginRequest
import com.example.finalproject.data.dto.response.LoginResponse
import com.example.finalproject.data.repository.UserRepository
import com.example.finalproject.data.service.dto.LoginState
import com.example.finalproject.data.repository.TokenManager
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo = UserRepository()
    val loginResult: MutableLiveData<LoginState<LoginResponse>> = MutableLiveData()

    // Fun iniciar sesión
    fun loginUser(email: String, password: String) {
        if (!isValidEmail(email) || !isValidPassword(password)) {
            loginResult.value = LoginState.Error("Email o contraseña inválida")
            return
        }
        loginResult.value = LoginState.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = password,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.isSuccessful == true) {
                    // Guarda el token de acceso
                    response.body()?.data?.accessToken?.let {
                        TokenManager.saveAuthToken(getApplication(), it)
                    }
                    loginResult.value = LoginState.Success(response.body())
                } else {
                    loginResult.value = LoginState.Error(response?.message())
                }
            } catch (ex: Exception) {
                loginResult.value = LoginState.Error(ex.message)
            }
        }
    }

    // Valida email
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{3}(\\.[A-Za-z]{2})?$".toRegex()
        return emailPattern.matches(email)
    }

    // Valida contraseña
    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$".toRegex()
        return passwordPattern.matches(password)
    }
}
