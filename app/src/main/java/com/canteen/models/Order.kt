package com.canteen.models

data class Order(
    val id: Int,
    val userId: Int,
    val totalPrice: Double,
    val status: String,
    val paymentMethod: String,
    val createdAt: String
)