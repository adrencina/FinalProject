package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product

class LeftbarApiServiceImp(private val apiService: LeftbarApiService) {

    // Implementación para obtener un producto por su id
    suspend fun getProductById(idProduct: Int): Product {
        return apiService.getProductById(idProduct)
    }

    // Implementación para obtener todos los productos
    suspend fun getAllProducts(): List<Product> {
        return apiService.getAllProducts()
    }

    // Implementación para obtener comentarios por id de producto
    suspend fun getComments(idProduct: Int): CommentsResponse {
        return apiService.getComments(idProduct)
    }

    // Implementación para obtener métodos de pago
    suspend fun getPaymentMethods(): PaymentMethodsResponse {
        return apiService.getPaymentMethods()
    }
}