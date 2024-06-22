package com.example.finalproject.data.service.dto

sealed class HomeState {
    //cambiar datos cuando se ocupe el home state
    data class Success(val info:String):HomeState()
    data class Error(val message: String):HomeState()
    data object Loading:HomeState()

}