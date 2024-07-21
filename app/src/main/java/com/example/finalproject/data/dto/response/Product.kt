package com.example.finalproject.data.dto.response

data class Product(
    val idProduct: Int?,
    val name: String?,
    val productType: ProductType?,
    val currency: String?,
    val price: Double?,
    val image: String?,
    val description: String?,
    val isFavorite: Boolean?
)
