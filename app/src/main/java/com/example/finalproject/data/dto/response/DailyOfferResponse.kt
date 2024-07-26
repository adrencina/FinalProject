package com.example.finalproject.data.dto.response

import com.example.finalproject.data.dto.model.Image

data class DailyOfferResponse(
    val idProduct: Int?,
    val name: String?,
    val productType: ProductType?,
    val currency: String?,
    val price: Double?,
    val images: List<Image>?,
    val description: String?,
    val dailyOffer: Boolean?,
    val isFavorite: Boolean?
)
