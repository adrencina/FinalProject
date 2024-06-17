package com.example.finalproject.ui.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Utils.PASSWORD_LOWERCASE_PATTERN
import com.example.finalproject.Utils.PASSWORD_NUMBER_PATTERN
import com.example.finalproject.Utils.PASSWORD_SPECIAL_CHARACTER_PATTERN
import com.example.finalproject.Utils.PASSWORD_UPPERCASE_PATTERN
import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.repository.RegisterRepository
import com.example.finalproject.data.service.RegisterApiServisImp
import com.example.finalproject.data.service.dto.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel(){
    private val repository = RegisterRepository(RegisterApiServisImp())
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun validateInputs(email: String, password: String, confirmPassword: String) {
        val emailValid = validateEmail(email)
        val passwordValid = validatePassword(password)
        val passwordsMatch = password == confirmPassword
        val passwordMatchError = if (!passwordsMatch) "Las contraseñas no coinciden" else null

        val allValid = emailValid && passwordValid && passwordsMatch
        if (allValid) {
            _registerState.value = RegisterState.Ready
        } else {
            _registerState.value = RegisterState.Validation(
                emailError = if (!emailValid) "Email inválido o requerido." else null,
                passwordError = if (!passwordValid || !passwordsMatch) passwordMatchError else null
            )
        }
    }

    private fun validateEmail(email: String): Boolean {
        val emailError = when {
            email.isEmpty() -> "Email requerido."
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email inválido."
            else -> null
        }
        _registerState.value = RegisterState.Validation(emailError = emailError)
        return emailError == null
    }

    private fun validatePassword(password: String): Boolean {
        val passwordError = when {
            password.length < 8 -> "Es necesario mínimo 8 caracteres."
            !PASSWORD_UPPERCASE_PATTERN.matcher(password).matches() -> "Se necesita mínimo 1 letra mayúscula."
            !PASSWORD_NUMBER_PATTERN.matcher(password).matches() -> "Se necesita mínimo 1 número."
            !PASSWORD_LOWERCASE_PATTERN.matcher(password).matches() -> "Se necesita mínimo 1 letra minúscula."
            !PASSWORD_SPECIAL_CHARACTER_PATTERN.matcher(password).matches() -> "Se necesita mínimo 1 caracter especial."
            else -> null
        }
        _registerState.value = RegisterState.Validation(passwordError = passwordError)
        return passwordError == null
    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val request = RegisterRequest(email, password)
            val result = repository.registerUser(request)
            _registerState.value = result
        }
    }
}