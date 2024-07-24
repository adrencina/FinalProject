package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.service.PaymentApiServiceImp
import retrofit2.Response

class PaymentRepository(private val context: Context) {

    private val apiService = PaymentApiServiceImp(context)

    suspend fun getPaymentMethods(): Response<PaymentMethodsResponse> {
        return apiService.getPaymentMethods()
    }
}