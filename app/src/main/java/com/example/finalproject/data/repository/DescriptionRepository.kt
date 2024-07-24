package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductResponse

import com.example.finalproject.data.service.LeftbarApiServiceImp
import retrofit2.Response

class DescriptionRepository(private val service: LeftbarApiServiceImp = LeftbarApiServiceImp()  ) {
    suspend fun getDescription(): Response<Product>{
        return service.getDescription()
    }
}