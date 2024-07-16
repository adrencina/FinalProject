package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.FavoriteProductRequest
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApiService {

    // Actualiza la oferta diaria
    @PUT("/api/v1/products/daily-offer")
    suspend fun updateDailyOffer(@Body request: FavoriteProductRequest): Response<Product>

    // Obtiene una lista de productos
    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("idProductType") idProductType: Int?,
        @Query("productName") productName: String?,
        @Query("onlyFavorite") onlyFavorite: Boolean = false,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Response<ProductResponse>

    // Crea un producto
    @POST("/api/v1/products")
    suspend fun createProduct(@Body request: NewProductRequest): Response<Product>

    // Marca producto como favorito
    @POST("/api/v1/products/{idProduct}/favorite")
    suspend fun markAsFavorite(@Path("idProduct") idProduct: Int): Response<Unit>

    // Obtiene los detalles de un producto
    @GET("/api/v1/products/{idProduct}")
    suspend fun getProductDetails(@Path("idProduct") idProduct: Int): Response<Product>

    // Obtiene una lista de ProductType
    @GET("/api/v1/product-types")
    suspend fun getProductTypes(): Response<ProductTypeResponse>

}