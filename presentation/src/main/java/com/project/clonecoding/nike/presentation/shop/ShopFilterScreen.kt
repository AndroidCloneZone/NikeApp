package com.project.clonecoding.nike.presentation.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray200
import com.project.clonecoding.nike.designsystem.theme.gray400
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.theme.white
import com.project.clonecoding.nike.designsystem.ui.BaseButton
import com.project.clonecoding.nike.designsystem.ui.BaseCheckbox
import com.project.clonecoding.nike.designsystem.ui.BaseRadioButton
import com.project.clonecoding.nike.designsystem.ui.ButtonStyle
import com.project.clonecoding.nike.designsystem.ui.CheckboxStyle
import com.project.clonecoding.nike.designsystem.ui.RadioButtonStyle
import com.project.clonecoding.nike.presentation.shop.item.FilterGender
import com.project.clonecoding.nike.presentation.shop.item.FilterSortBy
import com.project.clonecoding.nike.presentation.shop.item.ShopFilter

@Composable
fun ShopFilterScreen(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    // 현재 선택한 정렬 순서
    var selectedSortBy by rememberSaveable {
        mutableStateOf(state.value.shopFilter.sortBy)
    }
    // 현재 선택한 성별 목록
    var selectedGenderList by rememberSaveable {
        mutableStateOf(state.value.shopFilter.gender)
    }
    // 현재 선택한 브랜드 목록
    var selectedBrandList by rememberSaveable {
        mutableStateOf(state.value.shopFilter.brand)
    }
    // 현재 최소 가격 필터 (비활성화 시 null)
    var minPrice by rememberSaveable {
        mutableStateOf(state.value.shopFilter.minPrice)
    }
    // 현재 최대 가격 필터 (비활성화 시 null)
    var maxPrice by rememberSaveable {
        mutableStateOf(state.value.shopFilter.maxPrice)
    }
    // 클릭 시 차단 상태를 관리할 플래그
    var isExitCalled by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                // 로드된 브랜드 목록이 있고, 현재 불러오고 있지 않은 경우에만 수행
                if (state.value.loadedBrandList.isNotEmpty() && !state.value.isFilterBrandsLoading) {
                    // 마지막 아이템에 도착했을 때, 다음 페이지를 불러옴
                    if (lastVisibleItem != null && lastVisibleItem.index == listState.layoutInfo.totalItemsCount - 1) {
                        viewModel.onEvent(ShopEvent.FetchFilterBrands)
                    }
                }
            }
    }


    Scaffold {
        Column(
            modifier = Modifier
                .background(white)
                .padding(it)
        ) {
            ShopFilterScreenHeader(
                modifier = Modifier.fillMaxWidth(),
                onExit = {
                    if (!isExitCalled) {
                        // popBackStack 호출하고, 이후 호출 차단
                        navController.popBackStack()
                        isExitCalled = true
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = listState
            ) {
                item {
                    SortByArea(
                        selectedFilterSortBy = selectedSortBy,
                        onFilterSortBySelected = { fsb ->
                            selectedSortBy = fsb
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                item {
                    GenderArea(
                        selectedGenderList = selectedGenderList,
                        onFilterGenderSelected = { gender, isChecked ->
                            if (isChecked) {
                                val idx = selectedGenderList.indexOf(gender)
                                if (idx != -1) {
                                    val removedGenderList = ArrayList(selectedGenderList).apply {
                                        removeAt(idx)
                                    }
                                    selectedGenderList = removedGenderList
                                }
                            } else {
                                val addedGenderList = ArrayList(selectedGenderList).apply {
                                    add(gender)
                                }
                                selectedGenderList = addedGenderList
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                item {
                    ShopByPriceArea(
                        minPrice = minPrice,
                        maxPrice = maxPrice,
                        onMinPriceChanged = { price ->
                            minPrice = price.let { price?.toInt() }
                        },
                        onMaxPriceChanged = { price ->
                            maxPrice = price.let { price?.toInt() }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Column(
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 29.dp
                        ),
                    ) {
                        Text(
                            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_brand),
                            style = nikeTypography.text2XlMedium,
                            color = black
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                brandItems(
                    brandList = state.value.loadedBrandList,
                    selectedBrandList = selectedBrandList,
                    onFilterBrandSelected = { brand, isChecked ->
                        if (isChecked) {
                            val idx = selectedBrandList.indexOf(brand)
                            if (idx != -1) {
                                val removedBrandList = ArrayList(selectedBrandList).apply {
                                    removeAt(idx)
                                }
                                selectedBrandList = removedBrandList
                            }
                        } else {
                            val addedBrandList = ArrayList(selectedBrandList).apply {
                                add(brand)
                            }
                            selectedBrandList = addedBrandList
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                item {
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(gray200)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            FilterButtonArea(
                onReset = {
                    selectedSortBy = FilterSortBy.Like
                    selectedGenderList = listOf()
                    selectedBrandList = listOf()
                    minPrice = null
                    maxPrice = null
                },
                onApply = {
                    val newFilter = ShopFilter(
                        sortBy = selectedSortBy,
                        gender = selectedGenderList,
                        brand = selectedBrandList,
                        minPrice = minPrice,
                        maxPrice = maxPrice,
                    )
                    viewModel.onEvent(ShopEvent.ApplyFilter(newFilter))
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ShopFilterScreenHeader(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 36.5.dp)
    ) {

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_title),
            style = nikeTypography.displayMdMedium,
            color = black
        )

        Spacer(modifier = Modifier.width(16.dp))


        Icon(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .clickable {
                    onExit()
                },
            painter = painterResource(id = R.drawable.ic_filled_x_circle_24),
            contentDescription = "ShopFilterScreenCircleXIcon"
        )
    }
}

@Composable
private fun SortByArea(
    selectedFilterSortBy: FilterSortBy,
    onFilterSortBySelected: (FilterSortBy) -> Unit,
    modifier: Modifier = Modifier,
) {
    val filterSortByList =
        listOf(FilterSortBy.Like, FilterSortBy.Name, FilterSortBy.PriceAsc, FilterSortBy.PriceDesc)
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 21.dp),
    ) {
        Text(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_sort_by),
            style = nikeTypography.text2XlMedium,
            color = black
        )
        Spacer(modifier = Modifier.height(20.dp))

        filterSortByList.forEach { fsb ->
            Row(
                modifier = Modifier.clickable {
                    onFilterSortBySelected(fsb)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseRadioButton(
                    selected = fsb == selectedFilterSortBy,
                    style = RadioButtonStyle.WithRipple,
                    onSelectedChange = {
                        onFilterSortBySelected(fsb)
                    },
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = stringResource(id = fsb.strId),
                    style = nikeTypography.textLgRegular,
                    color = black
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(gray200)
    )
}

@Composable
private fun GenderArea(
    selectedGenderList: List<FilterGender>,
    onFilterGenderSelected: (FilterGender, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterGenderList =
        listOf(FilterGender.Male, FilterGender.Female, FilterGender.Unisex)
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 29.dp, bottom = 25.dp),
    ) {
        Text(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_gender),
            style = nikeTypography.text2XlMedium,
            color = black
        )

        Spacer(modifier = Modifier.height(20.dp))

        filterGenderList.forEach { gender ->
            val isChecked = selectedGenderList.contains(gender)
            Row(
                modifier = Modifier.clickable {
                    onFilterGenderSelected(gender, isChecked)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseCheckbox(
                    checked = isChecked,
                    style = CheckboxStyle.WithRipple,
                    onCheckedChange = {
                        onFilterGenderSelected(gender, isChecked)
                    },
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = stringResource(id = gender.strId),
                    style = nikeTypography.textLgRegular,
                    color = black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(gray200)
    )
}

@Composable
private fun ShopByPriceArea(
    minPrice: Int?,
    maxPrice: Int?,
    onMinPriceChanged: (String?) -> Unit,
    onMaxPriceChanged: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 29.dp, bottom = 29.dp),
    ) {
        Text(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_shop_by_price),
            style = nikeTypography.text2XlMedium,
            color = black
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 최소 가격
        PriceCheckInputBox(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_price_minimum),
            isSelected = minPrice != null,
            price = minPrice?.toString() ?: "",
            onCheckedChange = { isSelected ->
                val price = if (isSelected) "0" else null
                onMinPriceChanged(price)
            },
            onPriceChange = { price ->
                onMinPriceChanged(price)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 최대 가격
        PriceCheckInputBox(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_price_maximum),
            isSelected = maxPrice != null,
            price = maxPrice?.toString() ?: "",
            onCheckedChange = { isSelected ->
                // 체크 상태가 변경되었을 때를 트리거
                // 체크 시에는 default값인 100000을, 체크 해제 시에는 null을 할당
                val price = if (isSelected) "100000" else null
                onMaxPriceChanged(price)
            },
            onPriceChange = { price ->
                onMaxPriceChanged(price)
            },
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(gray200)
    )
}

private fun LazyListScope.brandItems(
    brandList: List<String>,
    selectedBrandList: List<String>,
    onFilterBrandSelected: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    brandList.forEach { brand ->
        val isChecked = selectedBrandList.contains(brand)
        checkBoxTextRow(
            text = brand,
            isChecked = isChecked,
            onClick = {
                onFilterBrandSelected(brand, isChecked)
            },
            modifier = modifier
        )
    }
}

private fun LazyListScope.checkBoxTextRow(
    text: String,
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    item {
        Row(
            modifier = modifier.clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            BaseCheckbox(
                checked = isChecked,
                style = CheckboxStyle.WithRipple,
                onCheckedChange = {
                    onClick()
                },
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = text,
                style = nikeTypography.textLgRegular,
                color = black
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun PriceCheckInputBox(
    text: String,
    isSelected: Boolean,
    price: String,
    onCheckedChange: (Boolean) -> Unit,
    onPriceChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tfColor = if (isSelected) Color.Transparent else gray200
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseCheckbox(
            checked = isSelected,
            style = CheckboxStyle.WithRipple,
            onCheckedChange = onCheckedChange
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = text,
            style = nikeTypography.textLgRegular,
            color = black
        )

        Spacer(modifier = Modifier.width(20.dp))

        BasicTextField(
            modifier = Modifier
                .width(100.dp)
                .border(width = 1.dp, shape = RoundedCornerShape(4.dp), color = gray400)
                .clip(RoundedCornerShape(4.dp))
                .background(tfColor)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            value = price,
            enabled = isSelected,
            onValueChange = { value ->
                // 최대 8자리 숫자(maximum 99,999,999)이고, 모두 숫자인 경우에만 반영되도록 함
                if (value.length < 9 && value.all { it.isDigit() }) {
                    // 빈 값이 들어가는 경우에는 Integer 변환 과정에서 에러가 발생하기에, 0을 넣어줌
                    if (value.isEmpty()) {
                        onPriceChange("0")
                    } else {
                        onPriceChange(value)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_price_unit),
            style = nikeTypography.textLgRegular,
            color = black
        )
    }

}

@Composable
private fun FilterButtonArea(
    onReset: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
        BaseButton(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_reset),
            style = ButtonStyle.EmptyDark,
            modifier = Modifier.weight(1f),
            onClick = onReset
        )

        Spacer(modifier = Modifier.width(9.dp))

        BaseButton(
            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_apply),
            style = ButtonStyle.FillDark,
            modifier = Modifier.weight(1f),
            onClick = onApply
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ShopFilterScreenPreview() {
    var selectedSortBy by rememberSaveable {
        mutableStateOf(FilterSortBy.Like)
    }
    var selectedGenderList by rememberSaveable {
        mutableStateOf(listOf<FilterGender>())
    }
    var selectedBrandList by rememberSaveable {
        mutableStateOf(listOf<String>())
    }
    var minPrice: Int? by rememberSaveable {
        mutableStateOf(null)
    }
    var maxPrice: Int? by rememberSaveable {
        mutableStateOf(null)
    }

    Scaffold {
        Column(
            modifier = Modifier
                .background(white)
                .padding(it)
        ) {
            ShopFilterScreenHeader(
                modifier = Modifier.fillMaxWidth(),
                onExit = {

                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    SortByArea(
                        selectedFilterSortBy = selectedSortBy,
                        onFilterSortBySelected = { fsb ->
                            selectedSortBy = fsb
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                item {
                    GenderArea(
                        selectedGenderList = selectedGenderList,
                        onFilterGenderSelected = { gender, isChecked ->
                            if (isChecked) {
                                val idx = selectedGenderList.indexOf(gender)
                                if (idx != -1) {
                                    val removedGenderList = ArrayList(selectedGenderList).apply {
                                        removeAt(idx)
                                    }
                                    selectedGenderList = removedGenderList
                                }
                            } else {
                                val addedGenderList = ArrayList(selectedGenderList).apply {
                                    add(gender)
                                }
                                selectedGenderList = addedGenderList
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                item {
                    ShopByPriceArea(
                        minPrice = minPrice,
                        maxPrice = maxPrice,
                        onMinPriceChanged = { price ->
                            minPrice = price.let { price?.toInt() }
                        },
                        onMaxPriceChanged = { price ->
                            maxPrice = price.let { price?.toInt() }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Column(
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 29.dp
                        ),
                    ) {
                        Text(
                            text = stringResource(id = com.project.clonecoding.nike.presentation.R.string.filter_brand),
                            style = nikeTypography.text2XlMedium,
                            color = black
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                brandItems(
                    brandList = listOf("나이키", "아디다스"),
                    selectedBrandList = selectedBrandList,
                    onFilterBrandSelected = { brand, isChecked ->
                        if (isChecked) {
                            val idx = selectedBrandList.indexOf(brand)
                            if (idx != -1) {
                                val removedBrandList = ArrayList(selectedBrandList).apply {
                                    removeAt(idx)
                                }
                                selectedBrandList = removedBrandList
                            }
                        } else {
                            val addedBrandList = ArrayList(selectedBrandList).apply {
                                add(brand)
                            }
                            selectedBrandList = addedBrandList
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                item {
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(gray200)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            FilterButtonArea(
                onReset = {
                    selectedSortBy = FilterSortBy.Like
                    selectedGenderList = listOf()
                    selectedBrandList = listOf()
                    minPrice = null
                    maxPrice = null
                },
                onApply = {

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}