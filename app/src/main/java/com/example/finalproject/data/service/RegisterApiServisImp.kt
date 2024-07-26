package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.dto.response.RegisterResponse
import retrofit2.Response

class RegisterApiServisImp(context: Context) {

    private val api = RetrofitInstance.getAuthRetrofit(context).create(RegisterApiService::class.java)

    suspend fun registerUser(request: RegisterRequest): Response<RegisterResponse> {
        return api.registerUser(request)
    }
}