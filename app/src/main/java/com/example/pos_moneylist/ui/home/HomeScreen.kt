package com.example.pos_moneylist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.ui.navigation.NavigationDestination
import com.example.pos_moneylist.ui.productArea.ProductArea
import com.example.pos_moneylist.ui.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.receiptArea.ReceiptArea
import com.example.pos_moneylist.ui.receiptArea.ReceiptAreaViewModel


object HomeDestination : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    productAreaViewModel: ProductAreaViewModel,
    receiptAreaViewModel: ReceiptAreaViewModel,
) {

    test(productAreaViewModel)

    Row {
        Column(
            Modifier.weight(0.6f)
        ) {
            ProductArea(
                productAreaViewModel = productAreaViewModel,
                onProductButtonClicked = { saleItem -> receiptAreaViewModel.addSaleItem(saleItem) })
        }

        Column(
            Modifier.weight(0.3f),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                ReceiptArea(receiptAreaViewModel = receiptAreaViewModel)
            }
        }

    }
}

fun test(productAreaViewModel: ProductAreaViewModel) {
    for (i in 0..5) {
        productAreaViewModel.addProduct(
            Product(
                name = "Product $i",
                price = 1.00f + i * 1.50f,
                Color.DarkGray
            )
        )
    }

    for (i in 6..10) {
        productAreaViewModel.addProduct(
            Product(
                name = "Product $i",
                price = 1.00f + i * 1.50f,
                Color.DarkGray
            )
        )
    }
}