package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.service.LeftbarApiServiceImp
import retrofit2.Response

class LeftbarRepository(context: Context) {
    private val apiServiceImp = LeftbarApiServiceImp(context)

    // Producto por su id
    suspend fun getProductById(idProduct: Int): Response<Product> {
        return apiServiceImp.getProductById(idProduct)
    }

    // Todos los productos
    suspend fun getAllProducts(): List<Product> {
        return apiServiceImp.getAllProducts()
    }


    // Métodos de pago
    suspend fun getPaymentMethods(): PaymentMethodsResponse {
        return apiServiceImp.getPaymentMethods()
    }

//    // Comentarios por id de producto
//    suspend fun getCommentsByProductId(idProduct: Int): Response<CommentsResponse> {
//        return apiServiceImp.getCommentsByProductId(idProduct)
//    }


}