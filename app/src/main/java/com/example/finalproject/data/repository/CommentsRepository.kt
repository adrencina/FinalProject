package com.example.finalproject.data.repository

import android.content.Context
import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.service.CommentsApiService
import com.example.finalproject.data.service.RetrofitInstance
import retrofit2.Response

class CommentsRepository(context: Context) {
    private val apiService: CommentsApiService = RetrofitInstance.getCommentsRetrofit(context).create(CommentsApiService::class.java)

    suspend fun getCommentsByProductId(idProduct: Int): Response<CommentsResponse> {
        return apiService.getCommentsByProductId(idProduct)
    }
}