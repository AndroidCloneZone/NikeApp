package com.project.clonecoding.nike.domain.model

data class ProductsSetModel(
    val products: List<ProductModel>,
    val nextStartAfter: String?,
)

data class ProductModel(
    val id: String,
    val name: String,
    val gender: String,
    val price: Int,
    val brand: String,
    val category: String,
    val imgPath: String,
    val likeCount: Int,
)