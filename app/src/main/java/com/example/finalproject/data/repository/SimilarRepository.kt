package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.service.HomeApiServiceImpl

class SimilarRepository(private val context: Context)  {
    private val apiService = HomeApiServiceImpl(context)

    suspend fun getSimilarProducts(idProduct: Int) = apiService.getSimilarProducts(idProduct)
}