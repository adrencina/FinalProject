package com.example.finalproject.data.service

import android.content.Context

class PaymentApiServiceImp(context: Context) : PaymentApiService {
    private val api = RetrofitInstance.getGeneralRetrofit(context).create(PaymentApiService::class.java)
    override suspend fun getPaymentMethods() = api.getPaymentMethods()
}

