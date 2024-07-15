package com.example.finalproject.data.dto.response

import com.google.gson.annotations.SerializedName

data class ProductType(
    @SerializedName("idProductType") val idType: Int?,
    @SerializedName("description") val description: String?
)
