package com.example.finalproject.data.service.dto

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("idProduct")
    val idProduct: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("productType")
    val productType: ProductType,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("description")
    val description: String
)
