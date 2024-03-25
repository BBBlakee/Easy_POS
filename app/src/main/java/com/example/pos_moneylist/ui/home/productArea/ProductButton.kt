package com.example.pos_moneylist.ui.home.productArea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

    Button(
        onClick = onClick,
        modifier = Modifier.padding(
            start = 5.dp,
            top = 5.dp,
            bottom = 5.dp,
            end = 5.dp
        ),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = name, fontSize = 30.sp)
            Text(text = "$price €", fontSize = 20.sp)
        }
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun ProductButtonPreview() {
    ProductButton(product = Product("Test langer Text", 9.99f, Color.DarkGray)) {

    }
}
