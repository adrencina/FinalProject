package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.dto.response.RegisterResponse
import com.example.finalproject.data.repository.BaseUrl
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RegisterApiServisImp {

    private val client = OkHttpClient.Builder()
        .connectTimeout(300, TimeUnit.SECONDS)
        .readTimeout(300, TimeUnit.SECONDS)
        .writeTimeout(300, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service = retrofit.create(RegisterApiService::class.java)
    suspend fun registerUser(request: RegisterRequest): Response<RegisterResponse> {
        return service.registerUser(request)
    }
}