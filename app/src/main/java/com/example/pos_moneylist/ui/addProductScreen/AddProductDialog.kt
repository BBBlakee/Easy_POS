package com.example.pos_moneylist.ui.addProductScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.productList.Product

/**
 * Dialog if a new product should be added to the product list. The dialog takes the name, price and
 * color and creates a new [Product]. The price text field checks, if the input is a currency. The name
 * text field checks, if the product already exists.
 * @param onDismissRequest What should happen if the user clicks outside the dialog?
 * @param onCancel What should happen, if the user cancels the dialog?
 * @param onConfirm What should happen, if the user confirms the dialog? Creates the product and adds
 * it to the product list.
 * @param onNameChange Function to check, if product already exists.
 */
@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductDialog(
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: (product: Product) -> Unit,
    onNameChange: (product: Product) -> Boolean = { false },
) {

    var productName: String by remember { mutableStateOf("") }
    var productPrice: String by remember { mutableStateOf("") }
    var productColor: Color by remember { mutableStateOf(Color.Red) }

    var nameIsValid: Boolean by remember { mutableStateOf(false) }
    var priceIsValid: Boolean by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(modifier = Modifier.background(Color.White)) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                //Product name
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.add_product_name)) },
                    value = productName,
                    onValueChange = {
                        nameIsValid =
                            !onNameChange(Product(it, 0.0f, Color.Black)) and it.isNotEmpty()
                        productName = it
                    },
                    colors = if (nameIsValid) {
                        TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Black)
                    } else {
                        TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Red)
                    }
                )

                //Product price
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.add_product_price)) },
                    value = productPrice,
                    onValueChange = {
                        priceIsValid = it.isNotEmpty()
                        productPrice = if (it.startsWith("0")) {
                            ""
                        } else {
                            it
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = if (priceIsValid) {
                        TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Black)
                    } else {
                        TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Red)
                    },
                    //visualTransformation = CurrencyAmountVisualTransformation()
                )

                //Product color
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.color)) },
                    value = productColor.toString(),
                    onValueChange = { /*TODO*/ })

                //Action buttons
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    //Cancel button
                    Button(onClick = onCancel) {
                        Text(text = stringResource(R.string.button_cancel))
                    }
                    //Confirm button
                    Button(
                        enabled = nameIsValid and priceIsValid,
                        onClick = {
                            onConfirm(
                                Product(
                                    name = productName,
                                    price = productPrice.toFloat(),
                                    color = productColor
                                )
                            )
                        }) {
                        Text(text = stringResource(R.string.button_confirm))
                    }
                }
            }
        }
    }
}