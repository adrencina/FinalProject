package com.example.finalproject.data.repository

import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.service.dto.HomeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val homeApiServiceImp: HomeApiServiceImp) {

    suspend fun getHomeData(): Result<HomeData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = HomeApiServiceImp().getHomeData()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("Response body is null"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}