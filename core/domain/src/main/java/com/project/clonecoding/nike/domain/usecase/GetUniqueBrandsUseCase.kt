package com.project.clonecoding.nike.domain.usecase

import com.project.clonecoding.nike.domain.repository.ProductRepository
import javax.inject.Inject

class GetUniqueBrandsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(
        limit: Int?,
        startAfter: String?,
    ) = productRepository.fetchUniqueBrands(
        limit = limit,
        startAfter = startAfter
    )
}