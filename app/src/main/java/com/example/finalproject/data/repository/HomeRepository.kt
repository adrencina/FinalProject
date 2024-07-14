package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.request.FavoriteProductRequest
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.service.HomeApiService

class HomeRepository(private val apiService: HomeApiService) {

    suspend fun updateDailyOffer(request: FavoriteProductRequest) =
        apiService.updateDailyOffer(request)

    suspend fun getProducts(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean,
        page: Int,
        size: Int
    ) = apiService.getProducts(idProductType, productName, onlyFavorite, page, size)

    suspend fun createProduct(request: NewProductRequest) = apiService.createProduct(request)

    suspend fun markAsFavorite(idProduct: Int) = apiService.markAsFavorite(idProduct)

    suspend fun getProductDetails(idProduct: Int) = apiService.getProductDetails(idProduct)

    suspend fun getProductTypes() = apiService.getProductTypes()
}