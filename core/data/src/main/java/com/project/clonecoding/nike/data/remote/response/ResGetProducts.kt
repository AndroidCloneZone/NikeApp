package com.project.clonecoding.nike.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResGetProducts(
    @SerializedName("products") val products: List<ResProductEntity>,
    @SerializedName("nextStartAfter") val nextStartAfter: String
)

data class ResProductEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("price") val price: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("category") val category: String,
    @SerializedName("imgPath") val imgPath: String,
    @SerializedName("likeCount") val likeCount: Int,
)