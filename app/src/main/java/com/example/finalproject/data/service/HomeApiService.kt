package com.example.finalproject.data.service

import com.example.finalproject.data.service.dto.HomeData
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {
    @GET("/home")
    suspend fun getHomeData(): Response<HomeData>
}