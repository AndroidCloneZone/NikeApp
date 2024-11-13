package com.project.clonecoding.nike.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResGetUniqueBrands(
    @SerializedName("brands") val brands: List<String>,
    @SerializedName("nextStartAfter") val nextStartAfter: String
)