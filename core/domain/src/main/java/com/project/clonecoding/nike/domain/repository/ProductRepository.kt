package com.project.clonecoding.nike.domain.repository

import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.domain.model.ProductModel
import com.project.clonecoding.nike.domain.model.ProductsSetModel
import com.project.clonecoding.nike.domain.model.UniqueBrandsSetModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    /**
     * 페이징 크기에 맞는 상품 목록을 가져온다.
     * @author 이유호
     * @param sortBy 정렬 순서
     * @param gender 성별 필터
     * @param brand 브랜드 필터
     * @param category 카테고리 필터
     * @param minPrice 최소 가격 필터
     * @param maxPrice 최대 가격 필터
     * @param limit 페이지 당 아이템 개수
     * @param startAfter 다음 페이지를 위한 현재 마지막 item ID
     */
    suspend fun fetchProductEntities(
        sortBy: String?,
        gender: String?,
        brand: String?,
        category: String?,
        minPrice: Int?,
        maxPrice: Int?,
        limit: Int?,
        startAfter: String?
    ): Flow<DataState<ProductsSetModel>>

    /**
     * 브랜드 목록을 가져온다.
     * @author 이유호
     * @param limit 페이지 당 아이템 개수
     * @param startAfter 다음 페이지를 위한 현재 마지막 item ID
     */
    suspend fun fetchUniqueBrands(
        limit: Int?,
        startAfter: String?
    ): Flow<DataState<UniqueBrandsSetModel>>
}