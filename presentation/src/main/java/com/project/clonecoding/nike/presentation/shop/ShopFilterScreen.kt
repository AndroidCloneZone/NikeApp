package com.project.clonecoding.nike.presentation.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.theme.white
import com.project.clonecoding.nike.designsystem.ui.BaseButton
import com.project.clonecoding.nike.designsystem.ui.ButtonStyle

@Composable
fun ShopFilterScreen(
    navHostController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel()
) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopFilterScreenPreview() {
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
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                SortByArea(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                GenderArea(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                ShopByPriceArea(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                ColorArea(modifier = Modifier.fillMaxWidth())
                BrandArea(modifier = Modifier.fillMaxWidth())
                SportsAndActivitiesArea(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                FilterButtonArea(modifier = Modifier.fillMaxWidth())
            }
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
            .padding(vertical = 24.dp, horizontal = 36.5.dp)
    ) {

        Text(
            modifier = Modifier.weight(1f),
            text = "Filter",
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
    modifier: Modifier = Modifier
) {
    Text(text = "Sort By", style = nikeTypography.text2XlMedium, color = black)
}

@Composable
private fun GenderArea(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun ShopByPriceArea(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun ColorArea(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun BrandArea(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun SportsAndActivitiesArea(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun FilterButtonArea(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(vertical = 20.dp)) {
        BaseButton(
            text = "Reset",
            style = ButtonStyle.EmptyDark,
            modifier = Modifier.weight(1f),
            onClick = {

            }
        )

        Spacer(modifier = Modifier.width(9.dp))

        BaseButton(
            text = "Apply",
            style = ButtonStyle.FillDark,
            modifier = Modifier.weight(1f),
            onClick = {

            }
        )
    }
}