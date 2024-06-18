package com.example.finalproject.ui.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.repository.RegisterRepository
import com.example.finalproject.data.service.dto.RegisterState
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {
    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> get() = _registerState

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _confirmPassword = MutableLiveData<String>()

    fun onEmailChanged(email: String) {
        _email.value = email
        validateInputs()
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
        validateInputs()
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
        validateInputs()
    }

    private fun validateInputs() {
        val email = _email.value ?: ""
        val password = _password.value ?: ""
        val confirmPassword = _confirmPassword.value ?: ""

        val isValidEmail = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isValidPassword = password.length >= 8 && password == confirmPassword

        _registerState.value = if (isValidEmail && isValidPassword) {
            RegisterState.Ready
        } else {
            RegisterState.Invalid
        }
    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val request = RegisterRequest(email, password)
            val result = registerRepository.registerUser(request)
            _registerState.value = result
        }
    }
}