package com.example.finalproject.ui.leftbar.fragments.financing.state

import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState

sealed class financingState {
    data class Success(val data: Product): financingState()

    data object Loading : financingState()

    data class Error(val message: String) : financingState()

}