package com.example.finalproject.data.dto.model

data class PaymentMethod(
    val idPaymentMethod: Int,
    val entity: String,
    val installments: List<Installment>
)
