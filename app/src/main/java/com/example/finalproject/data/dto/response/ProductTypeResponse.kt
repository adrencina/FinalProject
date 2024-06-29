package com.example.finalproject.data.dto.response

import com.google.gson.annotations.SerializedName

data class ProductTypeResponse(
    @SerializedName("productTypes") val productTypes: List<ProductType>
)
