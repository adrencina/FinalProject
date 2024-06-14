package com.example.finalproject.Data.Dto.Request

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
