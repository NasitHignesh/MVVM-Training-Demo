package com.example.mvvm.api.product.model

data class ProductDataItem(
    var category: String,
    var description: String,
    var id: Int,
    var image: String,
    var price: Double,
    var rating: ProductRating,
    var title: String
)