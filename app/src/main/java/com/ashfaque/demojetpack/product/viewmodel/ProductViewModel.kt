package com.ashfaque.demojetpack.product.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashfaque.demojetpack.core.RetrofitClient
import com.ashfaque.demojetpack.product.model.ProductsResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel() {
    private val _product = MutableLiveData<ProductsResponse>()
    val productsResponse: LiveData<ProductsResponse> = _product

    fun getProducts() {
        viewModelScope.launch {
            try {
                val fetchedPosts = RetrofitClient().retrofitClient.getProducts()
                _product.value = fetchedPosts

                Log.d("Ashu", Gson().toJson(fetchedPosts))

            } catch (e: Exception) {
                // Handle error

                Log.d("Ashu ","Exception "+ e.toString())
            }
        }
    }
}
