package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.FavoritesResponse
import com.example.finalproject.data.dto.response.LastUserProductResponse
import com.example.finalproject.data.dto.response.ProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {
    @GET("/api/v1/products")
    suspend fun getProducts(): Response<ProductResponse>

    @GET("/api/v1/products/daily-offer")
    suspend fun getDailyOffer(): Response<DailyOfferResponse>

    @GET("/api/v1/products/product-types")
    suspend fun getProductTypes(): Response<ProductTypeResponse>

    @GET("/api/v1/products/lasuserproduct")
    suspend fun getLastUserProduct(): Response<LastUserProductResponse>

    @GET("/api/v1/products/favorites")
    suspend fun getFavorites(): Response<FavoritesResponse>
}