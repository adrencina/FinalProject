package com.example.finalproject.ui.leftbar.fragments.comment.state

import com.example.finalproject.data.dto.response.Product

sealed class commProductState {
    data object Loading : commProductState()
    data class Success(val product: Product) : commProductState()
    data class Error(val message: String) : commProductState()
}