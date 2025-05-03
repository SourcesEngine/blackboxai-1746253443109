package com.canteen.models

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val role: String,
    val walletBalance: Double,
    val fullName: String?,
    val email: String?
)