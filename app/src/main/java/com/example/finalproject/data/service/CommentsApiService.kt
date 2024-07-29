package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.CommentsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsApiService {
    @GET("api/v1/comments")
    suspend fun getCommentsByProductId(
        @Query("idProduct") idProduct: Int
    ): Response<CommentsResponse>
}