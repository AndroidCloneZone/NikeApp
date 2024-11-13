package com.project.clonecoding.nike.data.remote.service

import com.project.clonecoding.nike.data.remote.response.ResGetProducts
import com.project.clonecoding.nike.data.remote.response.ResGetUniqueBrands
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("/getProducts")
    suspend fun fetchProducts(
        @Query("sortby") sortBy: String?,
        @Query("gender") gender: String?,
        @Query("brand") brand: String?,
        @Query("category") category: String?,
        @Query("minPrice") minPrice: Int?,
        @Query("maxPrice") maxPrice: Int?,
        @Query("limit") limit: Int?,
        @Query("startAfter") startAfter: String?,
    ): Response<ResGetProducts>

    @GET("/getUniqueBrands")
    suspend fun fetchUniqueBrands(
        @Query("limit") limit: Int?,
        @Query("startAfter") startAfter: String?,
    ): Response<ResGetUniqueBrands>


}