package com.example.pos_moneylist.ui.home.receiptArea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

    Column {
        Row {
            Column {
                Row {
                    Text(
                        text = String.format("%.2f EUR", total),
                        fontSize = 55.sp
                    )
                }

                Divider(
                    Modifier.padding(top = 5.dp, bottom = 15.dp),
                    thickness = 3.dp
                )
            }
        }

        Row(Modifier.weight(1.0f)) {
            LazyColumn {
                items(
                    items = saleItemList,
                    key = { it.name }
                ) { saleItem: SaleItem ->
                    ReceiptItem(
                        saleItem = saleItem,
                        onDeleteButtonClicked = { receiptAreaViewModel.removeSaleItem(saleItem) })
                }
                /*for (saleItem in saleItemList) {
                    item(key = saleItem.name) {
                        ReceiptItem(
                            saleItem = saleItem,
                            onDeleteButtonClicked = { receiptAreaViewModel.removeSaleItem(saleItem) })
                    }
                }
                 */
            }
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
                        text = stringResource(R.string.clear_button),
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}