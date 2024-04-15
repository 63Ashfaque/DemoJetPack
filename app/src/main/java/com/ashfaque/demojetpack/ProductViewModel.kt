package com.ashfaque.demojetpack

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashfaque.demojetpack.model.ProductsResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {
    private val _product = MutableLiveData<ProductsResponse>()
    val productsResponse: LiveData<ProductsResponse> = _product

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         apiService = retrofit.create(ApiService::class.java)
    }


    fun getProducts() {
        viewModelScope.launch {
            try {
                val fetchedPosts = apiService.getProducts()
                _product.value = fetchedPosts

                Log.d("Ashu", Gson().toJson(fetchedPosts))

            } catch (e: Exception) {
                // Handle error

                Log.d("Ashu ","Exception "+ e.toString())
            }
        }
    }
}
