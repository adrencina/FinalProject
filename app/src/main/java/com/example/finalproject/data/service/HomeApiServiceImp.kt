package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.FavoritesResponse
import com.example.finalproject.data.dto.response.LastUserProductResponse
import com.example.finalproject.data.dto.response.ProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import com.example.finalproject.data.repository.MockBaseUrl
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeApiServiceImp {
    private val retrofit = Retrofit.Builder()
        .baseUrl(MockBaseUrl.MOCK_BASE_URL) // URL del API Mock de Prueba.
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(HomeApiService::class.java)

    suspend fun getProducts(): Response<ProductResponse> {
        return service.getProducts()
    }

    suspend fun getDailyOffer(): Response<DailyOfferResponse> {
        return service.getDailyOffer()
    }

    suspend fun getProductTypes(): Response<ProductTypeResponse> {
        return service.getProductTypes()
    }

    suspend fun getLastUserProduct(): Response<LastUserProductResponse> {
        return service.getLastUserProduct()
    }

    //  todo esto va con el pronto consumo al endpoint de favorite
//    suspend fun addFavoritesProduct(id:Int): Response<FavoritesResponse>{
//        return service.addFavoritesProduct(id)
//    }
}
