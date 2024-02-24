package com.example.pos_moneylist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.ui.navigation.NavigationDestination
import com.example.pos_moneylist.ui.productArea.ProductArea
import com.example.pos_moneylist.ui.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.receiptArea.ReceiptArea
import com.example.pos_moneylist.ui.receiptArea.ReceiptAreaViewModel


object HomeDestination : NavigationDestination {
    override val route: String = "route"
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
            Row(Modifier.weight(1.0f, true)) {
                ReceiptArea(receiptAreaViewModel = receiptAreaViewModel)
            }

            Row {
                Column {

                    Divider(
                        Modifier.padding(top = 5.dp, bottom = 15.dp),
                        thickness = 3.dp
                    )

                    Button(
                        onClick = { receiptAreaViewModel.clear() },
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    ) {
                        Text(
                            text = "Clear",
                            fontSize = 30.sp
                        )
                    }
                }
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