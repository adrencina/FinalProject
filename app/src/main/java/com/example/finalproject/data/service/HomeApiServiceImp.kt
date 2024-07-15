package com.example.finalproject.data.service

import android.util.Log
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.FavoritesResponse
import com.example.finalproject.data.dto.response.LastUserProductResponse
import com.example.finalproject.data.dto.response.ProductResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import com.example.finalproject.data.repository.BaseUrl
import com.example.finalproject.data.repository.MockBaseUrl
import com.example.finalproject.data.repository.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")

        val modifiedRequest = builder.build()
        return chain.proceed(modifiedRequest)
    }
}


class HomeApiServiceImp {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(TokenManager.getTokenString()))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl.API_V1) // URL del API Mock de Prueba.
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val service = retrofit.create(HomeApiService::class.java)

    suspend fun getProducts(): Response<ProductResponse> {
        Log.i("HOLARETRO",TokenManager.getTokenString())
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
