package com.example.finalproject.Data.Dto.Model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("accessToken")
    var accessToken: String
)
