package com.example.finalproject

sealed class LoginState {
    //modificar datos de Success por el del response cuando se cree la clase login response
    data class Success(val info: String) : LoginState()
    data class Error(val message: String) : LoginState()
    data object Loading : LoginState()
}