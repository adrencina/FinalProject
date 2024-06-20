package com.example.finalproject.data.service

import com.example.finalproject.data.service.dto.HomeData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomeApiServiceImp {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api-users-c9xg.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create<HomeApiService>()
    suspend fun getHomeData(): Response<HomeData> {
        return service.getHomeData()
    }
}