package com.example.finalproject.data.repository

import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.service.dto.Category
import com.example.finalproject.data.service.dto.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val homeApiService: HomeApiServiceImp) {

    suspend fun getCategories(): Result<List<Category>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getCategories()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("No se encontraron categorías"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getProductsByCategory(categoryId: Int): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getProductsByCategory(categoryId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("No se encontraron productos para la categoría"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun searchProducts(
        query: String?,
        categoryId: Int?,
        color: String?,
        size: String?,
        gender: String?
    ): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.searchProducts(query, categoryId, color, size, gender)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    }
                        ?: Result.failure(Exception("No se encontraron productos para los criterios de búsqueda"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getOnSaleProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getOnSaleProducts()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("No se encontraron productos en oferta"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}