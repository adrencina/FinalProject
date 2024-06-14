package com.example.finalproject.Data.Service

import com.example.finalproject.Data.Dto.Request.LoginRequest
import com.example.finalproject.Data.Dto.Response.LoginResponse
import com.example.finalproject.Data.Repostory.RemoteDataSource
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