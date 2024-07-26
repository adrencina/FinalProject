package com.example.finalproject.data.service.dto

sealed class RegisterState {
    data object Loading : RegisterState()
    data class Success(val accessToken: String?) : RegisterState()
    data class Error(val message: String?) : RegisterState()
    data object Ready : RegisterState()
    data object Invalid : RegisterState()
    data class Validation(
        val emailError: String? = null,
        val passwordError: String? = null,
        val confirmPasswordError: String? = null
    ) : RegisterState()
}