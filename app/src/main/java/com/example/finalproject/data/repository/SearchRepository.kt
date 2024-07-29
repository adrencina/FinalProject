package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.service.HomeApiServiceImpl

class SearchRepository (context: Context) {

    private val apiService = HomeApiServiceImpl(context)


//    suspend fun getProducts(
//        idProductType: Int?,
//        productName: String?,
//        onlyFavorite: Boolean,
//        page: Int,
//        size: Int
//    ) = apiService.getProducts(idProductType, productName, onlyFavorite, page, size)

    suspend fun getProductsSearch(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean?,
        page: Int?,
        size: Int?
    ) = apiService.getProductsSearch(idProductType, productName, onlyFavorite, page, size)

}
