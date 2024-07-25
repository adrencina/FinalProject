package com.example.finalproject.ui.leftbar.fragments.description.state


import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductResponse


sealed class DescriptionState {
    data class Success(val data:Product): DescriptionState()

    data object Loading : DescriptionState()

    data class Error(val message: String) : DescriptionState()

}