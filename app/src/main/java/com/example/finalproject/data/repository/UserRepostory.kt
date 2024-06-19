package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.request.LoginRequest
import com.example.finalproject.data.dto.response.LoginResponse
import com.example.finalproject.data.service.AuthApi
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(loginRequest:LoginRequest): Response<LoginResponse>? {
        return  AuthApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}