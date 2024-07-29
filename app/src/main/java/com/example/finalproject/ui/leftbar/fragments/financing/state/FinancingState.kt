package com.example.finalproject.ui.leftbar.fragments.financing.state

import com.example.finalproject.data.dto.response.Product

sealed class FinancingState {
    data class Success(val data: Product): FinancingState()

    data object Loading : FinancingState()

    data class Error(val message: String) : FinancingState()

}