package com.example.finalproject

sealed class LoginState() {
    data class success(val info: String) : LoginState()
    data class error(val message: String) : LoginState()
    data object loading : LoginState()
}