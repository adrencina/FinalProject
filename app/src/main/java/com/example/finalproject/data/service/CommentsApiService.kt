package com.example.finalproject.data.service

import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentsApiService {
    @GET("api/v1/comments")
    suspend fun getCommentsByProductId(
        @Query("idProduct") idProduct: Int
    ): Response<CommentsResponse>
    @GET("api/v1/products/{idProduct}")
    suspend fun getProductById(@Path("idProduct") idProduct:Int): Product
}