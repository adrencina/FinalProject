package com.example.finalproject.data.dto.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("accessToken")
    var accessToken: String
)
