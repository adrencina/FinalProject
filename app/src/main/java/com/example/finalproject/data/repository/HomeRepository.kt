package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.service.HomeApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val homeApiService: HomeApiServiceImp) {

    suspend fun getCategories(): Result<List<ProductType>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getProductTypes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it.productTypes)
                    } ?: Result.failure(Exception("No se encontraron categorías"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getProducts()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it.products)
                    } ?: Result.failure(Exception("No se encontraron productos"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getOnSaleProducts(): Result<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getDailyOffer()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(
                            Product(
                                idProduct = it.idProduct,
                                name = it.name,
                                productType = it.productType,
                                currency = it.currency,
                                price = it.price,
                                image = it.image,
                                isFavorite = it.isFavorite,
                                description = it.description
                            )
                        )
                    } ?: Result.failure(Exception("No se encontraron productos en oferta"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getLastUserProduct(): Result<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getLastUserProduct()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(
                            Product(
                                idProduct = it.idProduct,
                                name = it.name,
                                productType = it.productType,
                                currency = it.currency,
                                price = it.price,
                                image = it.image,
                                isFavorite = it.isFavorite,
                                description = it.description
                            )
                        )
                    } ?: Result.failure(Exception("No se encontró el último producto del usuario"))
                } else {
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
