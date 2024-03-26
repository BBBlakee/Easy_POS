package com.example.pos_moneylist.ui.home.productArea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pos_moneylist.data.saleItemList.SaleItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductArea(
    productAreaViewModel: ProductAreaViewModel,
    onProductButtonClicked: (SaleItem) -> Unit,
) {

    val productList = remember { productAreaViewModel.productList.productList }



    LazyVerticalGrid(
        contentPadding = PaddingValues(10.dp),
        columns = GridCells.Adaptive(minSize = 250.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(40.dp))
            .fillMaxSize()
    ) {
        items(items = productList, key = { it.name }) { product ->
            ProductButton(
                product = product,
                onClick = { onProductButtonClicked(SaleItem(product)) })
        }

    }

    /*
    FlowRow(
        maxItemsInEachRow = 5
    ) {
        for (product in productList) {
            ProductButton(
                product = product,
                onClick = { onProductButtonClicked(SaleItem(product)) })
        }
    }

     */
}