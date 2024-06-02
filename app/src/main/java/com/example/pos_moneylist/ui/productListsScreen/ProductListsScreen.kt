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


package com.example.pos_moneylist.ui.productListsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pos_moneylist.Controller
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.data.productList.ProductList
import com.example.pos_moneylist.navigation.NavigationDestination
import com.example.pos_moneylist.ui.productListsScreen.addProductScreen.AddProductDialog
import com.example.pos_moneylist.ui.productListsScreen.productDetailsAndEditScreen.ProductDetailsAndEditDialog

object DestinationSettings : NavigationDestination {

    override val route: String = "settings"
}

@Composable
fun ProductListScreen(
    viewModel: ProductListsScreenViewModel,
    padding: PaddingValues,
) {

    val uiState by viewModel.uiState.collectAsState()

    val productLists = remember { viewModel.productLists }

    Scaffold(
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {

                ExtendedFloatingActionButton(
                    onClick = { viewModel.showAddListScreen() }, modifier = Modifier.padding(5.dp)
                ) {
                    Icon(
                        Icons.TwoTone.Add, contentDescription = "Add list button"
                    )
                    Text(text = stringResource(R.string.add_list))
                }
                if (uiState.currProductList != null) {
                    ExtendedFloatingActionButton(
                        onClick = { viewModel.showAddProductScreen() },
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Icon(
                            Icons.TwoTone.Add, contentDescription = "Add product button"
                        )
                        Text(
                            text = String.format(
                                stringResource(R.string.add_product_to_1s),
                                uiState.currProductList?.name
                            )
                        )
                    }
                }
            }

        }, modifier = Modifier.padding(padding)
    ) { innerPadding ->

        // If list is empty show empty list message - see elvis operator at the end of this block
        uiState.currProductList?.let { productList ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                //Product list selector
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    itemsIndexed(items = productLists,
                        key = { index: Int, item: ProductList -> item.name + index.toString() }) { index, list ->
                        OutlinedButton(
                            onClick = {
                                if (uiState.currListIndex == index) viewModel.showEditListScreen()
                                viewModel.setCurrentListIndex(viewModel.getListIndex(list.name))
                            }, border = if (uiState.currListIndex == index) {
                                BorderStroke(width = 3.dp, color = Color.Black)
                            } else {
                                BorderStroke(width = 1.dp, color = Color.LightGray)
                            }, modifier = Modifier.padding(5.dp)
                        ) {
                            Text(
                                text = list.name,
                                fontWeight = if (uiState.currListIndex == index) FontWeight.Bold else FontWeight.Normal
                            )
                            Spacer(Modifier.size(10.dp))
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit product"
                            )
                        }
                    }
                }

                //Product list
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .weight(1f)
                    ) {
                        items(
                            items = productList.productList,
                            key = { it.name }) { product: Product ->
                            ListItem(headlineContent = {
                                Text(
                                    text = product.name, fontSize = 25.sp
                                )
                            }, supportingContent = {
                                Text(
                                    text = String.format(
                                        "%.2f EUR", product.price
                                    ), color = Color.Gray
                                )
                            }, trailingContent = {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Edit icon"
                                )
                            }, modifier = Modifier
                                .padding(vertical = 5.dp)
                                .clickable {
                                    viewModel.setDetailedProduct(product)
                                    viewModel.showProductDetailsScreen()
                                })
                            HorizontalDivider(thickness = 1.dp)
                        }
                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                }
            }
        } ?: // Elvis from beginning of this block - uiState.currProductList?.let
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.empty_list), fontSize = 60.sp
            )
        }

        if (uiState.isAddProductScreenVisible) {
            AddProductDialog(onDismissRequest = { viewModel.hideAddProductScreen() },
                onCancel = { viewModel.hideAddProductScreen() },
                onConfirm = { product ->
                    viewModel.addProduct(uiState.currListIndex, product = product)
                    Controller.saveProductLists()
                    viewModel.hideAddProductScreen()
                },
                onNameChange = { product ->
                    viewModel.containsProduct(
                        uiState.currListIndex, product = product
                    )
                })
        }

        if (uiState.isProductDetailsScreenVisible) {
            ProductDetailsAndEditDialog(
                onDismissRequest = { viewModel.hideProductDetailsScreen() },
                onCancel = { viewModel.hideProductDetailsScreen() },
                onConfirm = {
                    Controller.saveProductLists()
                    viewModel.hideProductDetailsScreen()
                },
                onNameChange = { product ->
                    viewModel.containsProduct(
                        uiState.currListIndex, product = product
                    )
                },
                onDelete = { product ->

                    viewModel.removeProduct(uiState.currListIndex, product)
                    Controller.saveProductLists()
                    viewModel.hideProductDetailsScreen()
                },
                product = uiState.detailedProduct,
            )
        }

        if (uiState.isAddListScreenVisible) {
            AddProductListScreen(onDismissRequest = { viewModel.hideAddListScreen() },
                onConfirmButton = { listName ->
                    viewModel.addList(listName = listName)
                    Controller.saveProductLists()
                    viewModel.hideAddListScreen()
                }, onDismissButton = { viewModel.hideAddListScreen() },
                onValueChange = { listName -> !viewModel.containsList(listName) })
        }

        if (uiState.isEditListScreenVisible) {

            var currListName: String by remember {
                mutableStateOf(productLists[uiState.currListIndex].name)
            }
            var isNewNameValid: Boolean by remember {
                mutableStateOf(true)
            }
            var changesMade: Boolean by remember {
                mutableStateOf(false)
            }
            var showDeleteWarning: Boolean by remember {
                mutableStateOf(false)
            }

            Dialog(onDismissRequest = { viewModel.hideEditListScreen() }) {
                Card(modifier = Modifier.fillMaxWidth(0.6f)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                    ) {
                        //Title
                        Text(
                            text = stringResource(R.string.edit_list_title),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        //Content
                        OutlinedTextField(value = currListName,
                            onValueChange = {
                                isNewNameValid = !viewModel.containsList(it) and it.isNotEmpty()
                                changesMade = true
                                currListName = it
                            },
                            isError = !isNewNameValid,
                            label = { Text(text = stringResource(id = R.string.add_product_name)) })

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            //Delete button
                            TextButton(onClick = {
                                showDeleteWarning = true
                            }) {
                                Text(
                                    text = stringResource(id = R.string.button_delete),
                                    color = Color.Red
                                )
                            }

                            Spacer(modifier = Modifier.size(60.dp))

                            //Cancel button
                            TextButton(onClick = { viewModel.hideEditListScreen() }) {
                                Text(text = stringResource(id = R.string.button_cancel))
                            }
                            //Confirm button
                            TextButton(onClick = {
                                Controller.deleteProductList(productLists[uiState.currListIndex].name)
                                productLists[uiState.currListIndex].name = currListName
                                Controller.saveProductLists()
                                viewModel.hideEditListScreen()
                            }, enabled = isNewNameValid and changesMade) {
                                Text(text = stringResource(id = R.string.button_confirm))
                            }
                        }
                    }
                }
            }
            if (showDeleteWarning) {
                AlertDialog(onDismissRequest = { showDeleteWarning = false }, confirmButton = {
                    TextButton(onClick = {
                        Controller.deleteProductList(productLists[uiState.currListIndex].name)
                        viewModel.removeList(uiState.currListIndex)
                        Controller.saveProductLists()
                        viewModel.hideEditListScreen()
                        showDeleteWarning = false
                    }) {
                        Text(
                            text = stringResource(id = R.string.button_delete), color = Color.Red
                        )
                    }
                }, dismissButton = {
                    TextButton(onClick = { showDeleteWarning = false }) {
                        Text(text = stringResource(id = R.string.button_cancel))
                    }
                }, title = {
                    Text(
                        text = String.format(
                            "Really delete the list %1s", productLists[uiState.currListIndex].name
                        )
                    )
                })
            }
        }

    }
}