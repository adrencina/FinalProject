package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.dto.response.CommentsResponse
import retrofit2.Response

class CommentsApiServiceImp(context: Context) : CommentsApiService {

        private val api = RetrofitInstance.getCommentsRetrofit(context).create(CommentsApiService::class.java)

    override suspend fun getCommentsByProductId(idProduct: Int): Response<CommentsResponse> {
        return api.getCommentsByProductId(idProduct)
    }
}