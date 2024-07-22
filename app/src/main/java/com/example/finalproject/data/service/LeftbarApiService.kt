package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LeftbarApiService {

    // Obtener un producto por su id
    @GET("api/v1/products/{idProduct}")
    suspend fun getProductById(@Path("idProduct") idProduct: Int): Product

    // Obtener todos los productos
    @GET("api/v1/products")
    suspend fun getAllProducts(): List<Product>

    // Obtener comentarios por id de producto
    @GET("api/v1/comments")
    suspend fun getComments(@Query("idProduct") idProduct: Int): CommentsResponse

    // Obtener métodos de pago
    @GET("api/v1/payment-methods")
    suspend fun getPaymentMethods(): PaymentMethodsResponse
}