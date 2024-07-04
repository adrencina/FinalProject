package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.FavoritesResponse
import com.example.finalproject.data.dto.response.LastUserProductResponse
import com.example.finalproject.data.dto.response.ProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import com.example.finalproject.data.service.HomeApiServiceImp

class HomeRepository(private val apiService: HomeApiServiceImp) {

    suspend fun getProducts(): Result<ProductResponse> {
        return try {
            val response = apiService.getProducts()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("No se pudo obtener los productos"))
            } else {
                Result.failure(Exception("Error en la respuesta de productos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDailyOffer(): Result<DailyOfferResponse> {
        return try {
            val response = apiService.getDailyOffer()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("No se pudo obtener la oferta diaria"))
            } else {
                Result.failure(Exception("Error en la respuesta de oferta diaria"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductTypes(): Result<ProductTypeResponse> {
        return try {
            val response = apiService.getProductTypes()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("No se pudieron obtener los tipos de producto"))
            } else {
                Result.failure(Exception("Error en la respuesta de tipos de producto"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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

    suspend fun addFavoritesProduct(id:Int): Result<FavoritesResponse> {
        return try {
            val response = apiService.addFavoritesProduct(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("No se pudo obtener el articulo de Favoritos"))
            } else {
                Result.failure(Exception("Error en la respuesta de Favotitos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}


