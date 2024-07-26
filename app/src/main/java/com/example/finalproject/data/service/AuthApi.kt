package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.request.LoginRequest
import com.example.finalproject.data.dto.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    // Endpoint para el login de usuario
    @POST("/api/v1/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun create(context: Context): AuthApi {
            return RetrofitInstance.getAuthRetrofit(context).create(AuthApi::class.java)
        }
    }
}