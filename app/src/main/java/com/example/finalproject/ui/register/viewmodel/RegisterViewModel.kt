package com.example.finalproject.ui.register.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.service.dto.Utils.PASSWORD_LOWERCASE_PATTERN
import com.example.finalproject.data.service.dto.Utils.PASSWORD_NUMBER_PATTERN
import com.example.finalproject.data.service.dto.Utils.PASSWORD_SPECIAL_CHARACTER_PATTERN
import com.example.finalproject.data.service.dto.Utils.PASSWORD_UPPERCASE_PATTERN
import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.repository.RegisterRepository
import com.example.finalproject.data.service.RegisterApiServisImp
import com.example.finalproject.data.service.dto.RegisterState
import kotlinx.coroutines.launch

class RegisterViewModel(context: Context) : ViewModel() {
    private val registerRepository = RegisterRepository(RegisterApiServisImp(context))

    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> get() = _registerState

    // Registrar un usuario
    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val request = RegisterRequest(email, password)
            val result = registerRepository.registerUser(request)
            _registerState.value = result
        }
    }

    // Validaciones de entradas
    fun validateInputs(email: String, password: String, confirmPassword: String) {
        val emailError = when {
            email.isEmpty() -> "Email requerido."
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email inválido."
            else -> null
        }

        val passwordError = when {
            password.length < 8 -> "Es necesario mínimo 8 caracteres."
            !PASSWORD_UPPERCASE_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 letra mayúscula."

            !PASSWORD_NUMBER_PATTERN.matcher(password).matches() -> "Se necesita mínimo 1 número."
            !PASSWORD_LOWERCASE_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 letra minúscula."

            !PASSWORD_SPECIAL_CHARACTER_PATTERN.matcher(password)
                .matches() -> "Se necesita mínimo 1 caracter especial."

            else -> null
        }

        val confirmPasswordError = when {
            confirmPassword.isEmpty() -> "Confirmar Password."
            confirmPassword != password -> "Passwords no coinciden."
            else -> null
        }

        _registerState.value = RegisterState.Validation(
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        )

        if (emailError == null && passwordError == null && confirmPasswordError == null) {
            _registerState.value = RegisterState.Ready
        } else {
            _registerState.value = RegisterState.Invalid
        }
    }
}