package com.example.finalproject.ui.leftbar.fragments.description.state


import com.example.finalproject.data.dto.response.Product



sealed class DescriptionState {
    data object Loading :  DescriptionState()
    data class Success(val product: Product) : DescriptionState()
    data class Error(val message: String) : DescriptionState()

}