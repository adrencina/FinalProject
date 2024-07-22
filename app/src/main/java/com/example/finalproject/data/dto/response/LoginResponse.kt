package com.example.finalproject.data.dto.response

import com.example.finalproject.data.dto.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    var accessToken: String,
)