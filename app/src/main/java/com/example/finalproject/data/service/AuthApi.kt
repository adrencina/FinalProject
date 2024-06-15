package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.LoginRequest
import com.example.finalproject.data.dto.response.LoginResponse
import com.example.finalproject.data.repostory.RemoteDataSource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/v1/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): AuthApi? {
            return RemoteDataSource.client?.create(AuthApi::class.java)
        }
    }
}