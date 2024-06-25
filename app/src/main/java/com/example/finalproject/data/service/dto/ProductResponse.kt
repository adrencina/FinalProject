package com.example.finalproject.data.service.dto

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("products")
    val products: List<Product>
)
