package com.example.mvvm.api.product.model

data class AddProduct(
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: ProductRating,
    val title: String
)