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

package com.example.pos_moneylist.ui.productListsScreen.productDetailsAndEditScreen

import CurrencyAmountInputVisualTransformation
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@Composable
fun ProductDetailsAndEditDialog(
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    onNameChange: (product: Product) -> Boolean,
    onDelete: (product: Product) -> Unit,
    product: Product,
) {

    val colorList = remember { Controller.productColorList.productColorList }


    var productName: String by remember { mutableStateOf(product.name) }
    var productPrice: String by remember {
        mutableStateOf(
            (product.price * 10f).toString().replace(".", "")
        )
    }
    var productColor: Color by remember { mutableStateOf(product.color) }

    var isNameValid: Boolean by remember { mutableStateOf(true) }
    var isPriceValid: Boolean by remember { mutableStateOf(true) }
    var changesMade: Boolean by remember { mutableStateOf(false) }
    var showDeleteWarning: Boolean by remember { mutableStateOf(false) }

    val isConfirmButtonEnabled: Boolean = isNameValid and isPriceValid and changesMade

    Dialog(onDismissRequest = onDismissRequest) {
        Card(modifier = Modifier.fillMaxWidth(0.6f)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Product information",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                //Product name
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.add_product_name)) },
                    value = productName,
                    onValueChange = { name ->
                        isNameValid =
                            !onNameChange(Product(name, 0.0f, Color.Black)) and name.isNotEmpty()
                        changesMade = true
                        productName = name.trim()
                    },
                    colors = if (isNameValid) {
                        OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Black)
                    } else {
                        OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red)
                    }
                )

                //Product price
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.add_product_price)) },
                    value = productPrice,
                    onValueChange = { price ->
                        changesMade = true
                        isPriceValid = try {
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
                    colors = if (isPriceValid) {
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

                        Text(text = stringResource(R.string.color))
                        LazyRow {
                            items(items = colorList, key = { it.hashCode() }) { color ->

                                OutlinedButton(
                                    onClick = {
                                        productColor = color
                                        changesMade = true
                                    },
                                    colors = ButtonDefaults.outlinedButtonColors(containerColor = color),
                                    shape = CircleShape,
                                    border = if (productColor == color) {
                                        BorderStroke(width = 3.dp, color = Color.Black)
                                    } else {
                                        BorderStroke(width = 3.dp, color = Color.LightGray)
                                    },
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier.size(50.dp)
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
                    //Delete button
                    IconButton(
                        onClick = { showDeleteWarning = true },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = stringResource(id = R.string.button_delete),
                            tint = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
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
                            product.name = productName
                            product.price = productPrice.toFloat() / 100.00f
                            product.color = productColor
                            onConfirm()
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

    if (showDeleteWarning) {
        AlertDialog(
            onDismissRequest = { showDeleteWarning = false },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(product)
                    showDeleteWarning = false
                }) {
                    Text(text = stringResource(id = R.string.button_delete), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteWarning = false }) {
                    Text(text = stringResource(id = R.string.button_cancel))
                }
            },
            title = {
                Text(
                    text = String.format(
                        stringResource(R.string.delete_warning_title),
                        product.name
                    )
                )
            })
    }
}