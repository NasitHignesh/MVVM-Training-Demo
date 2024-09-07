package com.example.mvvm.api.product

import com.example.mvvm.api.product.model.AddProduct
import com.example.mvvm.api.product.model.ProductData
import com.example.mvvm.api.product.model.ProductDataItem
import retrofit2.Call
import retrofit2.http.*

interface ProductsRetrofitAPI {

    @GET("/products")
    fun getAllProduct(): Call<ProductData>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id:Int): Call<Void>

    @POST("/products")
    fun addNewProduct(@Body addProduct: AddProduct): Call<ProductDataItem>

}