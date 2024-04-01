package com.example.pos_moneylist.ui.home.productArea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.data.productList.Product

@Composable
fun ProductButton(
    product: Product,
    onClick: () -> Unit = {},
) {
    val name: String = product.name
    val price: Float = product.price
    val buttonColor = product.color

    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .padding(
                start = 5.dp, top = 5.dp, bottom = 5.dp, end = 5.dp
            )
            .defaultMinSize(minHeight = 100.dp)
            .fillMaxSize(),
        colors = ButtonDefaults.filledTonalButtonColors(containerColor = buttonColor),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = String.format("%.2f €", price),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}