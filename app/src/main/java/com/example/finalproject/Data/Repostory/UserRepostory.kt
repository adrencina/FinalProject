package com.example.finalproject.Data.Repostory

import com.example.finalproject.Data.Dto.Request.LoginRequest
import com.example.finalproject.Data.Dto.Response.LoginResponse
import com.example.finalproject.Data.Service.AuthApi
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  AuthApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}