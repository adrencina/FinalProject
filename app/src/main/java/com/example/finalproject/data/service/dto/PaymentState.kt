package com.example.finalproject.data.service.dto
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product

sealed class  PaymentState {
    //cambiar datos cuando se ocupe el payment state
    data class Success(val info:PaymentMethodsResponse?):PaymentState()
    data class Error(val message: String?):PaymentState()
    data object Loading:PaymentState()

}