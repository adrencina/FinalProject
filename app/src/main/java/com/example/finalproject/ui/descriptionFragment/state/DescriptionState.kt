package com.example.finalproject.ui.descriptionFragment.state


import com.example.finalproject.data.dto.response.ProductResponse


sealed class DescriptionState {
    data class Success(val data:ProductResponse):DescriptionState()

    data object Loading : DescriptionState()

    data class Error(val error: String) : DescriptionState()

}