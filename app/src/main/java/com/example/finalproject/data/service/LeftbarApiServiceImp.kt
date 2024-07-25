package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product

import retrofit2.Response


class LeftbarApiServiceImp(context: Context): LeftbarApiService {

    private val api = RetrofitInstance.getGeneralRetrofit(context).create(LeftbarApiService::class.java)
    override suspend fun getProductById(idProduct: Int): Product {
        return api.getProductById(idProduct)
    }

    override suspend fun getAllProducts(): List<Product> {
        return api.getAllProducts()
    }

    override suspend fun getComments(idProduct: Int): CommentsResponse {
        return api.getComments(idProduct)
    }

    override suspend fun getPaymentMethods(): PaymentMethodsResponse {
        return api.getPaymentMethods()
    }

    override suspend fun getDescription(): Response<Product> {
        return api.getDescription()
    }


}