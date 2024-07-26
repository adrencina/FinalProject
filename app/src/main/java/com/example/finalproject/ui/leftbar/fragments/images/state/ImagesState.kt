package com.example.finalproject.ui.leftbar.fragments.images.state

import com.example.finalproject.data.dto.response.Product

sealed class ImagesState {
    data object Loading : ImagesState()
    data class Success(val product: Product) : ImagesState()
    data class Error(val message: String) : ImagesState()
}