package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.dto.response.RegisterResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class RegisterApiServisImp {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api-users-c9xg.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service = retrofit.create<RegisterApiService>()
    suspend fun registerUser(request: RegisterRequest): Response<RegisterResponse> {
        return service.registerUser(request)
    }
}