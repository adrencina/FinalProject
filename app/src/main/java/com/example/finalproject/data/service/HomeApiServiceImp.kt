package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.ProductTypeResponse
import com.example.finalproject.data.repository.BaseUrl
import com.example.finalproject.data.repository.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.Request
import okhttp3.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = TokenManager.getToken(context) ?: ""
        val originalRequest: Request = chain.request()
        val requestWithToken: Request = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(requestWithToken)
    }
}

class HomeApiServiceImpl(context: Context) : HomeApiService {
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(context))
        .connectTimeout(60, TimeUnit.SECONDS) // Tiempo conexión
        .readTimeout(60, TimeUnit.SECONDS)    // Tiempo lectura
        .writeTimeout(60, TimeUnit.SECONDS)   // Tiempo escritura
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl.BASE_URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val api: HomeApiService = retrofit.create(HomeApiService::class.java)

    override suspend fun getDailyOffer(): retrofit2.Response<DailyOfferResponse> {
        return api.getDailyOffer()
    }

    override suspend fun getProducts(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean,
        page: Int,
        size: Int
    ) = api.getProducts(idProductType, productName, onlyFavorite, page, size)

    override suspend fun createProduct(request: NewProductRequest) = api.createProduct(request)

    override suspend fun markAsFavorite(idProduct: Int) = api.markAsFavorite(idProduct)

    override suspend fun getProductDetails(idProduct: Int) = api.getProductDetails(idProduct)

    override suspend fun getProductTypes(): retrofit2.Response<ProductTypeResponse> {
        return api.getProductTypes()
    }
}