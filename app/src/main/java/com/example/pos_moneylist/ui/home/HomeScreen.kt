package com.example.pos_moneylist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pos_moneylist.navigation.NavigationDestination
import com.example.pos_moneylist.ui.home.productArea.ProductArea
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptArea
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel


object DestinationHome : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    productAreaViewModel: ProductAreaViewModel,
    receiptAreaViewModel: ReceiptAreaViewModel,
    innerPadding: PaddingValues,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Column(
            Modifier.weight(0.6f)
        ) {
            ProductArea(
                productAreaViewModel = productAreaViewModel,
                onProductButtonClicked = { saleItem ->
                    receiptAreaViewModel.addSaleItem(
                        saleItem
                    )
                })
        }

        Column(
            Modifier
                .weight(0.3f)
                .padding(start = 10.dp),
        ) {
            Row {
                ReceiptArea(receiptAreaViewModel = receiptAreaViewModel)
            }
        }
    }
}