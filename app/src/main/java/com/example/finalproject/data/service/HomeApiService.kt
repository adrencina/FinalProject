package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.LastUserProductResponse
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

    // Obtiene la oferta diaria
    @GET("/api/v1/products/daily-offer")
    suspend fun getDailyOffer(): Response<DailyOfferResponse>

    // Obtiene una lista de productos
    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("idProductType") idProductType: Int?,
        @Query("productName") productName: String?,
        @Query("onlyFavorite") onlyFavorite: Boolean = false,
        @Query("page") page: Int ,
        @Query("size") size: Int
    ): Response<ProductResponse>

    // Obtiene una lista de productos
    @GET("/api/v1/products")
    suspend fun getProductsSearch(
        @Query("idProductType") idProductType: Int?,
        @Query("productName") productName: String?,
        @Query("onlyFavorite") onlyFavorite: Boolean?,
        @Query("page") page: Int?,
        @Query("size") size: Int?
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

    // Obtiene una lista de tipos de productos
    @GET("/api/v1/product-types")
    suspend fun getProductTypes(): Response<ProductTypeResponse>

    // Obtiene el último producto visitado del usuario
    @GET("/api/v1/products/lasuserproduct")
    suspend fun getLastUserProduct(): Response<LastUserProductResponse>
//
//    // Agrega un producto a la lista de favoritos
//    @PUT("/api/v1/products/{idProduct}/favorite")
//    suspend fun addFavoritesProduct(@Path("idProduct") idProduct: Int): Response<Unit>
//

   // Agrega un producto a la lista de favoritos
    @GET("/api/v1/products/{idProduct}/similar")
    suspend fun getSimilarProducts(@Path("idProduct") idProduct: Int): Response<ProductResponse>
}