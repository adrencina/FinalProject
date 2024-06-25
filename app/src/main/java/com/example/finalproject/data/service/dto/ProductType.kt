package com.example.finalproject.data.service.dto

import com.google.gson.annotations.SerializedName

data class ProductType(
    @SerializedName("idType")
    val idType: Int,
    @SerializedName("descripcion")
    val descripcion: String
)
