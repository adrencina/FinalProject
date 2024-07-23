package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product

class LeftbarApiServiceImp(private val apiService: LeftbarApiService) {

    suspend fun getProductById(idProduct: Int): Product {
        return apiService.getProductById(idProduct)
    }

    suspend fun getAllProducts(): List<Product> {
        return apiService.getAllProducts()
    }

    suspend fun getComments(idProduct: Int): CommentsResponse {
        return apiService.getComments(idProduct)
    }

    suspend fun getPaymentMethods(): PaymentMethodsResponse {
        return apiService.getPaymentMethods()
    }
}