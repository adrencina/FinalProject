package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.Product
import retrofit2.Response

class CommentsApiServiceImp(context: Context) : CommentsApiService {

    private val api = RetrofitInstance.getCommentsRetrofit(context).create(CommentsApiService::class.java)
    private val apiService = RetrofitInstance.getGeneralRetrofit(context).create(LeftbarApiService::class.java)

    override suspend fun getCommentsByProductId(idProduct: Int): Response<CommentsResponse> {
        return api.getCommentsByProductId(idProduct)
    }

    override suspend fun getProductById(idProduct: Int): Product {
        return apiService.getProductById(idProduct)
        }
}