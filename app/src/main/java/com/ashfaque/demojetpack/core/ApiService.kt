package com.ashfaque.demojetpack.core

import com.ashfaque.demojetpack.product.model.ProductsResponse
import retrofit2.http.GET


interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductsResponse // Assuming Post is your model class
}