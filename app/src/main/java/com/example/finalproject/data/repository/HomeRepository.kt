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
                val response = homeApiService.getProductTypes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it.productTypes)
                    } ?: Result.failure(Exception("No categories found"))
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
                val response = homeApiService.getProducts()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val productsByCategory = it.products.filter { product -> product.productType.idType == categoryId }
                        Result.success(productsByCategory)
                    } ?: Result.failure(Exception("No products found for category"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun searchProducts(query: String?, categoryId: Int?, color: String?, size: String?, gender: String?): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getProducts()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val filteredProducts = it.products.filter { product ->
                            (query == null || product.name.contains(query, ignoreCase = true)) &&
                                    (categoryId == null || product.productType.idType == categoryId) &&
                                    (color == null || product.description.contains(color, ignoreCase = true)) &&
                                    (size == null || product.description.contains(size, ignoreCase = true)) &&
                                    (gender == null || product.description.contains(gender, ignoreCase = true))
                        }
                        Result.success(filteredProducts)
                    } ?: Result.failure(Exception("No products found for search criteria"))
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
                val response = homeApiService.getDailyOffer()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(listOf(it))
                    } ?: Result.failure(Exception("No on sale products found"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
