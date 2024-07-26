package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.service.RetrofitInstance

object RemoteDataSource {

    fun getAuthClient(context: Context) = RetrofitInstance.getAuthRetrofit(context)

    fun getGeneralClient(context: Context) = RetrofitInstance.getGeneralRetrofit(context)

    fun getPaymentClient(context: Context) = RetrofitInstance.getPaymentRetrofit(context)
}