package com.example.finalproject.data.service

import com.example.finalproject.data.service.dto.Category
import com.example.finalproject.data.service.dto.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

    @GET("categories/{categoryId}/products")
    suspend fun getProductsByCategory(@Path("categoryId") categoryId: Int): Response<List<Product>>

    @GET("products/search")
    suspend fun searchProducts(
        @Query("query") query: String?,
        @Query("categoryId") categoryId: Int?,
        @Query("color") color: String?,
        @Query("size") size: String?,
        @Query("gender") gender: String?
    ): Response<List<Product>>

    @GET("products/on_sale")
    suspend fun getOnSaleProducts(): Response<List<Product>>
}