package com.example.mvvm.api.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.api.product.model.AddProduct
import com.example.mvvm.api.product.model.ProductData
import com.example.mvvm.api.product.model.ProductDataItem
import com.example.mvvm.api.product.model.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(application: Application) {

    private var allProduct: MutableLiveData<ProductData> = MutableLiveData()
    fun callAPI(): MutableLiveData<ProductData> {
        val call = RetrofitClientInstance.getRetrofitInstance().getAllProduct()
        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("call", "${response.body().toString()}")
                        allProduct.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                println("t.message = ${t.message}")
            }
        })
        return allProduct
    }

    fun deleteCallAPI(id: Int) {
        val call = RetrofitClientInstance.getRetrofitInstance().deleteProduct(id)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {

                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("del", "not gate data")
            }
        })
    }

    fun addProductCallAPI(addProduct: AddProduct) {
        val call = RetrofitClientInstance.getRetrofitInstance().addNewProduct(addProduct)

        call.enqueue(object : Callback<ProductDataItem> {
            override fun onResponse(call: Call<ProductDataItem>, response: Response<ProductDataItem>) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("add", "done")
                    }
                }
            }

            override fun onFailure(call: Call<ProductDataItem>, t: Throwable) {
                Log.d("del", "not gate data")
            }
        })
    }

}