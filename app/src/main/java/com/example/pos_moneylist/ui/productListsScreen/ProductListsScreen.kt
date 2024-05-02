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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun SettingsScreen(
    productListsScreenViewModel: ProductListsScreenViewModel,
    innerPadding: PaddingValues,
) {

    val productLists = remember { productListsScreenViewModel.productLists }
    var productDetails: Product = remember { Product("No product", 0.00f, Color.Black) }

    var showProductList: Boolean by remember { mutableStateOf(false) }
    var showAddProductScreen: Boolean by remember { mutableStateOf(false) }
    var showProductDetailsScreen: Boolean by remember { mutableStateOf(false) }
    var showAddListScreen: Boolean by remember { mutableStateOf(false) }
    var showEditListScreen: Boolean by remember { mutableStateOf(false) }

    var selectedListIndex by remember { mutableIntStateOf(0) }

    showProductList = productLists.isNotEmpty()

    Scaffold(
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {

                ExtendedFloatingActionButton(
                    onClick = { showAddListScreen = true },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Icon(
                        Icons.TwoTone.Add, contentDescription = "Add list button"
                    )
                    Text(text = "Add list")
                }
                if (showProductList) {
                    ExtendedFloatingActionButton(
                        onClick = { showAddProductScreen = true },
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Icon(
                            Icons.TwoTone.Add, contentDescription = "Add product button"
                        )
                        Text(
                            text = String.format(
                                "Add product to %1s", productLists[selectedListIndex].name
                            )
                        )
                    }
                }
            }

        }, modifier = Modifier.padding(innerPadding)
    ) { innerPadding ->
        if (showProductList) {
            productListsScreenViewModel.sortLists()
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
                                if (selectedListIndex == index) showEditListScreen = true
                                selectedListIndex =
                                    productListsScreenViewModel.getListIndex(list.name)
                            },
                            border = if (selectedListIndex == index) BorderStroke(
                                width = 3.dp,
                                color = Color.Black
                            ) else BorderStroke(width = 1.dp, color = Color.LightGray),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(
                                text = list.name,
                                fontWeight = if (selectedListIndex == index) FontWeight.Bold else FontWeight.Normal
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
                        items(items = productLists[selectedListIndex].productList,
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
                                    productDetails = product
                                    showProductDetailsScreen = true
                                })
                            HorizontalDivider(thickness = 1.dp)
                        }
                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                }
            }

        } else {
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
        }

        if (showAddProductScreen) {
            AddProductDialog(onDismissRequest = { showAddProductScreen = false },
                onCancel = { showAddProductScreen = false },
                onConfirm = { product ->
                    productListsScreenViewModel.addProduct(selectedListIndex, product = product)
                    productListsScreenViewModel.sortLists()
                    Controller.saveProductLists()
                    showAddProductScreen = false
                },
                onNameChange = { product ->
                    productListsScreenViewModel.containsProduct(
                        selectedListIndex, product = product
                    )
                })
        }

        if (showProductDetailsScreen) {
            ProductDetailsAndEditDialog(
                onDismissRequest = { showProductDetailsScreen = false },
                onCancel = { showProductDetailsScreen = false },
                onConfirm = {
                    productListsScreenViewModel.sortLists()
                    Controller.saveProductLists()
                    showProductDetailsScreen = false
                },
                onNameChange = { product ->
                    productListsScreenViewModel.containsProduct(
                        selectedListIndex, product = product
                    )
                },
                onDelete = { product ->

                    productListsScreenViewModel.removeProduct(selectedListIndex, product)
                    productListsScreenViewModel.sortLists()
                    Controller.saveProductLists()
                    showProductDetailsScreen = false
                },
                product = productDetails,
            )
        }

        if (showAddListScreen) {
            AddProductListScreen(onDismissRequest = { showAddListScreen = false },
                onConfirmButton = { listName ->
                    productListsScreenViewModel.addList(listName = listName)
                    productListsScreenViewModel.sortLists()
                    Controller.saveProductLists()
                    showAddListScreen = false
                },
                onDismissButton = { showAddListScreen = false },
                onValueChange = { listName -> !productListsScreenViewModel.containsList(listName) })
        }

        if (showEditListScreen) {
            AlertDialog(
                onDismissRequest = { showEditListScreen = false },
                confirmButton = {
                    TextButton(onClick = {
                        Controller.deleteProductList(productLists[selectedListIndex].name)
                        productListsScreenViewModel.removeList(selectedListIndex)
                        selectedListIndex = 0
                        productListsScreenViewModel.sortLists()
                        Controller.saveProductLists()
                        showEditListScreen = false
                    }) {
                        Text(text = stringResource(id = R.string.button_delete))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEditListScreen = false }) {
                        Text(text = stringResource(id = R.string.button_cancel))
                    }
                },
                title = {
                    Text(
                        text = String.format(
                            "Really delete the list \"%1s\"?",
                            productLists[selectedListIndex].name
                        )
                    )
                })
        }
    }
}