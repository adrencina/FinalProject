package com.example.finalproject.data.service.dto

import com.google.gson.annotations.SerializedName

data class ProductTypeResponse(
    @SerializedName("productTypes")
    val productTypes: List<Category>
)
