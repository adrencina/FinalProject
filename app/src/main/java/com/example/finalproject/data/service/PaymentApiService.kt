package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PaymentApiService {
    @GET("/api/v1/payment-methods")
    suspend fun getPaymentMethods(): Response<PaymentMethodsResponse>
}




