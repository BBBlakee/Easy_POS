package com.example.pos_moneylist.ui.home.productArea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.saleItemList.SaleItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductArea(
    productAreaViewModel: ProductAreaViewModel,
    onProductButtonClicked: (SaleItem) -> Unit,
) {

    val productList = remember { productAreaViewModel.productList.productList }

    if (productList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.empty_list),
                fontSize = 60.sp,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyVerticalGrid(
            contentPadding = PaddingValues(10.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(25.dp))
                .fillMaxSize()
        ) {
            items(items = productList, key = { it.name }) { product ->
                ProductButton(
                    product = product,
                    onClick = { onProductButtonClicked(SaleItem(product)) })
            }
        }
    }
}