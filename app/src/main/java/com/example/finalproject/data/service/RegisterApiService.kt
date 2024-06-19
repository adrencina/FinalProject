package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.dto.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {
    @POST("/api/v1/auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>
}