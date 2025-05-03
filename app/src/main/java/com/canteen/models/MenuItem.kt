package com.canteen.models

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Double,
    val stock: Int,
    val imageUrl: String?,
    val available: Boolean,
    val category: String?
)