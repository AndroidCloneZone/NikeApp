package com.project.clonecoding.nike.domain.model

data class UniqueBrandsSetModel(
    val brands: List<String>,
    val nextStartAfter: String?,
)