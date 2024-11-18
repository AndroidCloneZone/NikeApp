package com.project.clonecoding.nike.presentation.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.domain.usecase.GetProductsUseCase
import com.project.clonecoding.nike.domain.usecase.GetUniqueBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getUniqueBrandsUseCase: GetUniqueBrandsUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<ShopState> = MutableStateFlow(ShopState())
    val state get() = _state.asStateFlow()

    init {
        fetchProducts()
        fetchBrands()
    }

    fun onEvent(event: ShopEvent) {
        when (event) {
            is ShopEvent.FetchFilterBrands -> {
                fetchBrands()
            }

            is ShopEvent.FetchProducts -> {
                fetchProducts()
            }

            is ShopEvent.ChangeCategory -> {
                _state.value = _state.value.copy(
                    productList = listOf(),
                    category = event.category,
                    productStartAfter = null,
                )
                fetchProducts()
            }

            is ShopEvent.ApplyFilter -> {
                _state.value = _state.value.copy(
                    productList = listOf(),
                    shopFilter = event.filter,
                    productStartAfter = null,
                )
                fetchProducts()
            }
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getProductsUseCase(
                sortBy = _state.value.shopFilter.sortBy.value,
                gender = _state.value.shopFilter.gender.joinToString(separator = ";") { it.value },
                brand = _state.value.shopFilter.brand.joinToString(separator = ";") { it },
                category = _state.value.category.value,
                minPrice = _state.value.shopFilter.minPrice,
                maxPrice = _state.value.shopFilter.maxPrice,
                limit = _state.value.productLimit,
                startAfter = _state.value.productStartAfter,
            ).collect { result ->
                when (result) {
                    is DataState.Loading -> {
                        _state.value = _state.value.copy(isProductsLoading = result.isLoading)
                    }

                    is DataState.Success -> {
                        result.data?.let { model ->
                            // 가장 마지막 페이지에서 호출하는 경우에 nextStartAfter가 null로 초기화 되어, 처음부터 다시 불러옴.
                            // 이를 막기 위해, null일때에는 이전에 할당된 값을 그대로 사용
                            _state.value = _state.value.copy(
                                productList = _state.value.productList.plus(model.products),
                                productStartAfter = model.nextStartAfter
                                    ?: _state.value.productStartAfter
                            )
                        }
                    }

                    is DataState.Error -> {
                        _state.value = _state.value.copy(isProductsLoading = false)
                    }
                }
            }
        }
    }

    private fun fetchBrands() {
        viewModelScope.launch {
            getUniqueBrandsUseCase(
                limit = _state.value.brandLimit,
                startAfter = _state.value.brandStartAfter
            ).collect { result ->
                when (result) {
                    is DataState.Loading -> {
                        _state.value = _state.value.copy(isFilterBrandsLoading = result.isLoading)
                    }

                    is DataState.Success -> {
                        result.data?.let { model ->
                            // 가장 마지막 페이지에서 호출하는 경우에 nextStartAfter가 null로 초기화 되어, 처음부터 다시 불러옴.
                            // 이를 막기 위해, null일때에는 이전에 할당된 값을 그대로 사용
                            _state.value = _state.value.copy(
                                loadedBrandList = _state.value.loadedBrandList.plus(model.brands),
                                brandStartAfter = model.nextStartAfter
                                    ?: _state.value.brandStartAfter
                            )
                        }
                    }

                    is DataState.Error -> {
                        _state.value = _state.value.copy(isFilterBrandsLoading = false)
                    }
                }
            }
        }
    }
}