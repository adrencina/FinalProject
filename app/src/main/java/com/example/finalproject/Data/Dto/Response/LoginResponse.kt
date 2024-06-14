package com.example.finalproject.Data.Dto.Response

import com.example.finalproject.Data.Dto.Model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var `data`: User,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
)