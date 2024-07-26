package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.dto.request.LoginRequest
import com.example.finalproject.data.dto.response.LoginResponse
import com.example.finalproject.data.service.AuthApi
import retrofit2.Response

class UserRepository(private val context: Context) {

    private val authApi: AuthApi by lazy {
        AuthApi.create(context)
    }

    // Llama a la API para el login de usuario
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return authApi.loginUser(loginRequest)
    }
}