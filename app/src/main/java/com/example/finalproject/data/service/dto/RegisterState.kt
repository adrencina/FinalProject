package com.example.finalproject.data.service.dto

import com.example.finalproject.data.dto.response.RegisterResponse

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data class Success(val response: RegisterResponse): RegisterState()
    data class Error(val message: String?) : RegisterState()
    data class Validation(val emailError: String? = null, val passwordError: String? = null) : RegisterState()
    data object Ready : RegisterState()
}


    //modificar datos de Success por el del response cuando se cree la class RegisterResponse
//    data class Success(val info: String) : RegisterState()
//    data class Error(val message: String) : RegisterState()
//    data object Loading : RegisterState()
