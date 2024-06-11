package com.example.finalproject

sealed class RegisterState {
    //modificar datos de Success por el del response cuando se cree la class RegisterResponse
    data class Success(val info: String) : RegisterState()
    data class Error(val message: String) : RegisterState()
    data object Loading : RegisterState()
}