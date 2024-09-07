package com.example.mvvm.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.api.product.model.AddProduct
import com.example.mvvm.api.product.model.ProductData
import com.example.mvvm.api.product.ProductRepository

class ProductViewModel(application: Application) : AndroidViewModel(application){
    private val repository: ProductRepository = ProductRepository(application)

    fun loadData(): MutableLiveData<ProductData>? {
        return repository.callAPI()
    }

    fun delProduct(id:Int){
        return repository.deleteCallAPI(id)
    }

    fun addProduct(addProduct: AddProduct){
        return repository.addProductCallAPI(addProduct)
    }

}