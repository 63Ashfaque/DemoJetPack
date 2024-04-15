package com.ashfaque.demojetpack

import com.ashfaque.demojetpack.model.ProductsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductsResponse // Assuming Post is your model class
}