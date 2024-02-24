package com.example.pos_moneylist.ui.productArea

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.pos_moneylist.data.saleItemList.SaleItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductArea(
    productAreaViewModel: ProductAreaViewModel,
    onProductButtonClicked: (SaleItem) -> Unit,
) {

    val productList = remember { productAreaViewModel.productList }

    FlowRow(
        maxItemsInEachRow = 5
    ) {
        for (product in productList) {
            ProductButton(
                product = product,
                onClick = { onProductButtonClicked(SaleItem(product)) })
        }
    }

}