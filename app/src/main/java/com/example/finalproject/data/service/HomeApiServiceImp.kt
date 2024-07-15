package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.FavoriteProductRequest
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.repository.BaseUrl
import com.example.finalproject.data.repository.MockBaseUrl
import com.example.finalproject.data.repository.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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



class HomeApiServiceImpl : HomeApiService {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(TokenManager.getToken()))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl.BASE_URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val api: HomeApiService = retrofit.create(HomeApiService::class.java)

    override suspend fun updateDailyOffer(request: FavoriteProductRequest) =
        api.updateDailyOffer(request)

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
    override suspend fun getProductTypes() = api.getProductTypes()
}
