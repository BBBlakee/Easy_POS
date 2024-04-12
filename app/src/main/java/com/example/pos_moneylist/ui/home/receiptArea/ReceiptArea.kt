package com.example.pos_moneylist.ui.home.receiptArea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.saleItemList.SaleItem

@Composable
fun ReceiptArea(
    receiptAreaViewModel: ReceiptAreaViewModel,
) {

    val saleItemList = remember { receiptAreaViewModel.saleItemList.saleItemList }
    val total by remember { receiptAreaViewModel.saleItemList.total }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row {
            Column {

            }
        }

        Row(Modifier.weight(1.0f)) {
            LazyColumn {
                items(
                    items = saleItemList,
                    key = { it.name }
                ) { saleItem: SaleItem ->
                    ReceiptItem(
                        name = saleItem.name,
                        counter = saleItem.counter,
                        price = saleItem.price,
                        total = saleItem.getTotalPrice(),
                        onMinusButtonClicked = { receiptAreaViewModel.removeSaleItem(saleItem) },
                        onPlusButtonClicked = { receiptAreaViewModel.addSaleItem(saleItem) })
                }
            }
        }
        Row {
            Column {

                HorizontalDivider(
                    Modifier.padding(vertical = 10.dp),
                    thickness = 2.dp
                )

                Row {
                    Text(
                        text = String.format("%.2f EUR", total),
                        fontSize = 55.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                HorizontalDivider(
                    Modifier.padding(vertical = 10.dp),
                    thickness = 2.dp
                )

                Button(
                    onClick = { receiptAreaViewModel.clear() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3C8B40)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                ) {
                    Text(
                        text = stringResource(R.string.clear_button),
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}