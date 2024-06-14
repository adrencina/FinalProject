package com.example.finalproject.Data.Service.dto

import com.google.gson.annotations.SerializedName

data class LoginApiError(
    val code: Int,
    val message: String
)

data class LoginResponses(
    @SerializedName("message")
    val message: List<String>?,
    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val error: LoginApiError? = null
)