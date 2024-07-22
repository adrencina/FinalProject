package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.service.LeftbarApiServiceImp

class LeftbarRepository(private val apiServiceImp: LeftbarApiServiceImp) {

    // Obtener un producto por su id
    suspend fun getProductById(idProduct: Int): Product {
        return apiServiceImp.getProductById(idProduct)
    }

    // Obtener todos los productos
    suspend fun getAllProducts(): List<Product> {
        return apiServiceImp.getAllProducts()
    }

    // Obtener comentarios por id de producto
    suspend fun getComments(idProduct: Int): CommentsResponse {
        return apiServiceImp.getComments(idProduct)
    }

    // Obtener métodos de pago
    suspend fun getPaymentMethods(): PaymentMethodsResponse {
        return apiServiceImp.getPaymentMethods()
    }
}