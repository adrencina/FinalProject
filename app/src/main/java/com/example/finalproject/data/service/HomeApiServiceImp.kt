package com.example.finalproject.data.service

import com.example.finalproject.data.repository.BaseUrl
import com.example.finalproject.data.repository.MockBaseUrl
import com.example.finalproject.data.service.dto.Product
import com.example.finalproject.data.service.dto.ProductResponse
import com.example.finalproject.data.service.dto.ProductTypeResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeApiServiceImp {
    private val retrofit = Retrofit.Builder()

//        .baseUrl(BaseUrl.BASE_URL) URL de la Api que está en proceso...

        .baseUrl(MockBaseUrl.MOCK_BASE_URL) // URL del API Mock de Prueba.
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(HomeApiService::class.java)

    suspend fun getProducts(): Response<ProductResponse> {
        return service.getProducts()
    }

    suspend fun getDailyOffer(): Response<Product> {
        return service.getDailyOffer()
    }

    suspend fun getProductTypes(): Response<ProductTypeResponse> {
        return service.getProductTypes()
    }

    suspend fun getLastUserProduct(): Response<Product> {
        return service.getLastUserProduct()
    }
}