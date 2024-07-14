package com.example.finalproject.data.service

import com.example.finalproject.data.dto.request.FavoriteProductRequest
import com.example.finalproject.data.dto.request.NewProductRequest
import com.example.finalproject.data.repository.MockBaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeApiServiceImpl : HomeApiService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(MockBaseUrl.MOCK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
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
