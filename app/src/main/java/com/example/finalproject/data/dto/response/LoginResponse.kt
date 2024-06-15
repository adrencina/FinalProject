package com.example.finalproject.data.dto.response

import com.example.finalproject.data.dto.model.User
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