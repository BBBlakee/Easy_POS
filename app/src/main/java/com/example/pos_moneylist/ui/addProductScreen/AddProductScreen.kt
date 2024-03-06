package com.example.pos_moneylist.ui.addProductScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.pos_moneylist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(modifier: Modifier = Modifier) {


    var productName: String by remember { mutableStateOf("") }
    var productPrice: String by remember { mutableStateOf("") }

    Box {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.add_product_name))
            TextField(value = productName, onValueChange = { productName = it })
            Text(text = stringResource(R.string.add_product_price))
            TextField(
                value = productPrice, onValueChange = { productPrice = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.button_cancel))
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.button_confirm))
                }
            }
        }
    }
}

@Preview
@Composable
fun AddProductScreenPreview() {
    AddProductScreen()
}