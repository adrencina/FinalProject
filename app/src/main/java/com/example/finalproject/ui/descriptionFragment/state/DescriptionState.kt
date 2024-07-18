package com.example.finalproject.ui.descriptionFragment.state

import com.example.finalproject.data.dto.response.Product


sealed class DescriptionState {
    data class Success(val data:Product):DescriptionState() // cambiar valor data a su respectivo response
    data class Error(val message:String):DescriptionState()

    data object Loading : DescriptionState()
}