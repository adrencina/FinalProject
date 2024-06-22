package com.example.finalproject.data.service.dto

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val color: String,
    val size: String,
    val gender: String,
    val categoryId: Int,
    val isOnSale: Boolean
)
