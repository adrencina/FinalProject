package com.example.finalproject.Data.Service.dto

sealed class LoginState<out T> {
    data class Success<out T>(val data: T? = null) : LoginState<T>()
    data class Loading(val nothing: Nothing?=null) : LoginState<Nothing>()
    data class Error(val msg: String?) : LoginState<Nothing>()
}