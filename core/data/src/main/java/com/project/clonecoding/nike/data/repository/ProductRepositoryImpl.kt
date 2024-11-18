package com.project.clonecoding.nike.data.repository

import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.data.remote.service.ProductService
import com.project.clonecoding.nike.domain.model.ProductModel
import com.project.clonecoding.nike.domain.model.ProductsSetModel
import com.project.clonecoding.nike.domain.model.UniqueBrandsSetModel
import com.project.clonecoding.nike.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {
    override suspend fun fetchProductEntities(
        sortBy: String?,
        gender: String?,
        brand: String?,
        category: String?,
        minPrice: Int?,
        maxPrice: Int?,
        limit: Int?,
        startAfter: String?
    ): Flow<DataState<ProductsSetModel>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val response = productService.fetchProducts(
                    sortBy = sortBy,
                    gender = gender,
                    brand = brand,
                    category = category,
                    minPrice = minPrice,
                    maxPrice = maxPrice,
                    limit = limit,
                    startAfter = startAfter,
                )
                val res = response.body()
                if (response.isSuccessful && response.code() == 200) {
                    val model = ProductsSetModel(
                        products = res?.products?.map { product ->
                            ProductModel(
                                id = product.id,
                                name = product.name,
                                gender = product.gender,
                                price = product.price,
                                brand = product.brand,
                                category = product.category,
                                imgPath = product.imgPath,
                                likeCount = product.likeCount,
                            )
                        } ?: listOf(),
                        nextStartAfter = res?.nextStartAfter
                    )
                    emit(DataState.Success(data = model))
                } else {
                    emit(DataState.Error("${response.errorBody()}"))
                }
            } catch (e: Exception) {
                emit(DataState.Error("$e"))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }

        }
    }

    override suspend fun fetchUniqueBrands(
        limit: Int?,
        startAfter: String?
    ): Flow<DataState<UniqueBrandsSetModel>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val response = productService.fetchUniqueBrands(
                    limit = limit,
                    startAfter = startAfter,
                )
                val res = response.body()
                if (response.isSuccessful && response.code() == 200) {
                    val model = UniqueBrandsSetModel(
                        brands = res?.brands ?: listOf(),
                        nextStartAfter = res?.nextStartAfter
                    )
                    emit(DataState.Success(data = model))
                } else {
                    emit(DataState.Error("${response.errorBody()}"))
                }
            } catch (e: Exception) {
                emit(DataState.Error("$e"))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }
    }
}