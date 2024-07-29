package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.LastUserProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import com.example.finalproject.data.service.HomeApiServiceImpl
import retrofit2.Response

class SearchRepository (private val context: Context) {

    private val apiService = HomeApiServiceImpl(context)


    suspend fun getProducts(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean,
        page: Int,
        size: Int
    ) = apiService.getProducts(idProductType, productName, onlyFavorite, page, size)

    suspend fun getProductsSearch(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean?,
        page: Int?,
        size: Int?
    ) = apiService.getProductsSearch(idProductType, productName, onlyFavorite, page, size)

//  todo esto va con el pronto consumo al endpoint de favorite
//    suspend fun addFavoritesProduct(id:Int):Response<FavoritesResponse>{
//        return apiService.addFavoritesProduct(id)
//    }
}
