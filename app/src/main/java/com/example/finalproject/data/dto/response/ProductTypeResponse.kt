package com.example.finalproject.data.dto.response

import com.example.finalproject.data.service.dto.Category
import com.google.gson.annotations.SerializedName

data class ProductTypeResponse(
    @SerializedName("productTypes")
    val productTypes: List<Category>
)
