/*
 *     The app is a simple point of sale system, mainly developed for small clubs without a
 *     point of sale system. It was developed to simplify the calculation of the total price.
 *
 *     Copyright (C) 2024 Michael Gamperling
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.example.pos_moneylist.ui.productListsScreen.addProductScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pos_moneylist.Controller
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.ui.visualtransformations.CurrencyAmountInputVisualTransformation

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
@Composable
fun AddProductDialog(
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: (product: Product) -> Unit,
    onNameChange: (product: Product) -> Boolean,
) {

    var productName: String by remember { mutableStateOf("") }
    var productPrice: String by remember { mutableStateOf("") }
    var productColor: Color by remember { mutableStateOf(Color.Unspecified) }

    var nameIsValid: Boolean by remember { mutableStateOf(false) }
    var priceIsValid: Boolean by remember { mutableStateOf(false) }

    val isConfirmButtonEnabled: Boolean = nameIsValid and priceIsValid

    val colorList = remember { Controller.productColorList.productColorList }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(modifier = Modifier.fillMaxWidth(0.6f)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Add Product",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                //Product name
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.add_product_name)) },
                    value = productName,
                    onValueChange = { name ->
                        nameIsValid =
                            !onNameChange(Product(name, 0.0f, Color.Black)) and name.isNotEmpty()
                        productName = name
                    },
                    colors = if (nameIsValid) {
                        OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Black)
                    } else {
                        OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red)
                    }
                )

                //Product price
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.price)) },
                    value = productPrice,
                    onValueChange = { price ->
                        priceIsValid = try {
                            price.toFloat()
                            true
                        } catch (e: NumberFormatException) {
                            false
                        }

                        productPrice = if (price.startsWith("0")) {
                            ""
                        } else {
                            price.trim()
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = if (priceIsValid) {
                        OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Black)
                    } else {
                        OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red)
                    },
                    visualTransformation = CurrencyAmountInputVisualTransformation()
                )

                //Product color - TODO change later to ColorSelector
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    ) {

                        var selectedColor: Color by remember { mutableStateOf(Color.Unspecified) }

                        Text(text = stringResource(R.string.color))
                        LazyRow {
                            items(items = colorList, key = { it.hashCode() }) { color ->

                                OutlinedButton(
                                    onClick = {
                                        productColor = color
                                        selectedColor = color
                                    },
                                    colors = ButtonDefaults.outlinedButtonColors(containerColor = color),
                                    shape = CircleShape,
                                    border = if (selectedColor == color) {
                                        BorderStroke(width = 3.dp, color = Color.Black)
                                    } else {
                                        BorderStroke(width = 3.dp, color = Color.LightGray)
                                    },
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier
                                        .size(50.dp)

                                ) {

                                }
                            }
                        }
                    }
                }

                //Action buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    //Cancel button
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = stringResource(id = R.string.button_cancel)
                        )
                    }
                    //Confirm button
                    IconButton(
                        enabled = isConfirmButtonEnabled,
                        onClick = {
                            onConfirm(
                                Product(
                                    name = productName.trim(),
                                    price = productPrice.toFloat() / 100.00f,
                                    color = productColor
                                )
                            )
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.button_confirm),
                            tint = if (isConfirmButtonEnabled) Color.Green else LocalContentColor.current
                        )
                    }
                }
            }
        }
    }
}