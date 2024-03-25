package com.example.pos_moneylist.ui.home.receiptArea

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.data.saleItemList.SaleItem

@Composable
fun ReceiptItem(
    saleItem: SaleItem,
    onDeleteButtonClicked: () -> Unit = {},
) {

    val counter by remember { saleItem.counter }
    val name = saleItem.name

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier.weight(1.0f, true)
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                modifier = Modifier.padding(
                    start = 3.dp,
                    end = 20.dp
                )
            )

        }
        Column {
            Text(
                text = "${counter}x",
                fontSize = 30.sp,
                textAlign = TextAlign.End
            )

        }
        Column {
            RemoveSaleItemButton(onClick = onDeleteButtonClicked)
        }
    }

}

@Composable
fun RemoveSaleItemButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.twotone_remove_circle),
            contentDescription = "Remove icon",
            modifier = Modifier.size(48.dp),
        )
    }
}

@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440,orientation=landscape",
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun ReceiptItemPreview() {
    ReceiptItem(saleItem = SaleItem(Product("Test product", 9.99f, Color.White)))
}