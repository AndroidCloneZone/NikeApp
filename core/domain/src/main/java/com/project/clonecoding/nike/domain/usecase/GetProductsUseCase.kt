package com.project.clonecoding.nike.domain.usecase

import com.project.clonecoding.nike.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(
        sortBy: String?,
        gender: String?,
        brand: String?,
        category: String?,
        minPrice: Int?,
        maxPrice: Int?,
        limit: Int?,
        startAfter: String?,
    ) = productRepository.fetchProductEntities(
        sortBy = sortBy,
        gender = gender,
        brand = brand,
        category = category,
        minPrice = minPrice,
        maxPrice = maxPrice,
        limit = limit,
        startAfter = startAfter,
    )
}