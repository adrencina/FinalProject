package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.request.NewProductRequest

class HomeApiServiceImpl(context: Context) : HomeApiService {

    private val api = RetrofitInstance.getGeneralRetrofit(context).create(HomeApiService::class.java)

    override suspend fun getDailyOffer() = api.getDailyOffer()

    override suspend fun getProducts(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean,
        page: Int,
        size: Int
    ) = api.getProducts(idProductType, productName, onlyFavorite, page, size)

    override suspend fun getProductsSearch(
        idProductType: Int?,
        productName: String?,
        onlyFavorite: Boolean?,
        page: Int?,
        size: Int?
    ) = api.getProductsSearch(idProductType, productName, onlyFavorite, page, size)

    override suspend fun createProduct(request: NewProductRequest) = api.createProduct(request)

    override suspend fun markAsFavorite(idProduct: Int) = api.markAsFavorite(idProduct)

    override suspend fun getProductDetails(idProduct: Int) = api.getProductDetails(idProduct)

    override suspend fun getProductTypes() = api.getProductTypes()

    override suspend fun getLastUserProduct() = api.getLastUserProduct()

    override suspend fun getSimilarProducts(idProduct: Int) = api.getSimilarProducts(idProduct)
}

    //  todo esto va con el pronto consumo al endpoint de favorite
//    suspend fun addFavoritesProduct(id:Int): Response<FavoritesResponse>{
//        return service.addFavoritesProduct(id)
//    }