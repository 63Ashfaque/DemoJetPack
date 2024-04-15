package com.ashfaque.demojetpack.model

import com.ashfaque.demojetpack.model.Product

data class ProductsResponse(
    val limit: Int,
    val products: List<Product>,
   val skip: Int,
    val total: Int
)