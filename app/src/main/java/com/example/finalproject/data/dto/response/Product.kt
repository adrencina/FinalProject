package com.example.finalproject.data.dto.response

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("idProduct") val idProduct: Int = 0,
    @SerializedName("name") val name: String? = "No info",
    @SerializedName("productType") val productType: ProductType? = null,
    @SerializedName("currency") val currency: String? = "No info",
    @SerializedName("price") val price: String? = "No info",
    @SerializedName("image") val image: String? = null,
    @SerializedName("isFavorite") val isFavorite: Boolean = false,
    @SerializedName("description") val description: String? = "No info"
)
