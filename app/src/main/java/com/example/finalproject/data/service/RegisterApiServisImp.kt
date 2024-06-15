package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.dto.response.RegisterResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RegisterApiServisImp {
    private val retrofit = Retrofit.Builder()
    .baseUrl("https://api-users-c9xg.onrender.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    private val service = retrofit.create<RegisterApiService>()
    suspend fun registerUser(request: RegisterRequest): Response<RegisterResponse> {
        return service.registerUser(request)
    }
}