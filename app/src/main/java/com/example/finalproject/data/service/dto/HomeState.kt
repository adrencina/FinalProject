package com.example.finalproject.data.service.dto

import com.example.finalproject.data.dto.response.FavoritesResponse


sealed class HomeState {
    //cambiar datos cuando se ocupe el home state
    data class Success(val info:FavoritesResponse?):HomeState()
    data class Error(val message: String?):HomeState()
    data object Loading:HomeState()

}