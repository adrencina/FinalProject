package com.example.finalproject.data.dto.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("accessToken")
    val accessToken: String?
)