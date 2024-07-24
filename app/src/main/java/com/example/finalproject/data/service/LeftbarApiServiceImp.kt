package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class LeftbarApiServiceImp {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api-products-fe4p.onrender.com")//api momentanea pero url original
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create<LeftbarApiService>()

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
    suspend fun getDescription(): Response<Product>{
        return apiService.getDescription()
    }
}