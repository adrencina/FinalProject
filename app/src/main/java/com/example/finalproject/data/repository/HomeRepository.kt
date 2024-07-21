package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.LastUserProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import retrofit2.Response
import com.example.finalproject.data.service.HomeApiServiceImpl

class HomeRepository(private val context: Context) {

    private val apiService = HomeApiServiceImpl(context)

    suspend fun getDailyOffer(): Response<DailyOfferResponse> {
        return apiService.getDailyOffer()
    }

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

    suspend fun getProductTypes(): Response<ProductTypeResponse> = apiService.getProductTypes()

    suspend fun getLastUserProduct(): Result<LastUserProductResponse> {
        return try {
            val response = apiService.getLastUserProduct()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("No se pudo obtener el último producto del usuario"))
            } else {
                Result.failure(Exception("Error en la respuesta del último producto del usuario"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//  todo esto va con el pronto consumo al endpoint de favorite

//    suspend fun addFavoritesProduct(id:Int):Response<FavoritesResponse>{
//        return apiService.addFavoritesProduct(id)
//    }
}

