package com.example.finalproject.data.service.dto

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idProductType")
    val idProductType: Int,
    @SerializedName("description")
    val description: String
)
