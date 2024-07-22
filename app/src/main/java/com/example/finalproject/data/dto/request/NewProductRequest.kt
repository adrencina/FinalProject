package com.example.finalproject.data.dto.request

import com.example.finalproject.data.dto.model.NewImage

data class NewProductRequest(
    val name: String,
    val idType: Int,
    val currency: String,
    val price: Double,
    val description: String,
    val largeDescription: String,
    val images: List<NewImage>
)
