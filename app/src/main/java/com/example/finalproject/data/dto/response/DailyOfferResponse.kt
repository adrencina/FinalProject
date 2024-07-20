package com.example.finalproject.data.dto.response

import com.google.gson.annotations.SerializedName

data class DailyOfferResponse(
    @SerializedName("idProduct") val idProduct: Int,
    @SerializedName("name") val name: String,
    @SerializedName("productType") val productType: ProductType,
    @SerializedName("currency") val currency: String,
    @SerializedName("price") val price: String,
    @SerializedName("images") val image: String,
    @SerializedName("isFavorite") val isFavorite: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("dailyOffer") val dailyOffer: Boolean
)
