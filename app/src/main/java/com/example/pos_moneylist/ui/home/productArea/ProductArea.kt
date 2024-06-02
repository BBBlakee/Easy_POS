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

package com.example.pos_moneylist.ui.home.productArea

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.productList.ProductList
import com.example.pos_moneylist.data.saleItemList.SaleItem

@Composable
fun ProductArea(
    modifier: Modifier = Modifier,
    viewModel: ProductAreaViewModel,
    gridColumns: Int = 2,
    onProductButtonClicked: (SaleItem) -> Unit,
) {

    val productLists = remember { viewModel.productLists }
    var selectedListIndex by remember { mutableIntStateOf(0) }

    viewModel.sortLists()

    var showProductList: Boolean by remember { mutableStateOf(false) }
    showProductList = productLists.isNotEmpty()

    if (!showProductList) {
        Box(
            contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.empty_list),
                fontSize = 60.sp,
                textAlign = TextAlign.Center
            )
        }
    } else {
        Column {
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
                            selectedListIndex = viewModel.getListIndex(list.name)
                        },
                        border = if (selectedListIndex == index) BorderStroke(
                            width = 3.dp, color = Color.Black
                        ) else BorderStroke(width = 1.dp, color = Color.LightGray),
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = list.name,
                            fontWeight = if (selectedListIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
            LazyVerticalGrid(
                contentPadding = PaddingValues(10.dp),
                columns = GridCells.Fixed(gridColumns),
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = productLists[selectedListIndex].productList,
                    key = { it.name }) { product ->
                    ProductButton(name = product.name,
                        price = product.price,
                        color = if (product.color == Color.Unspecified) ButtonDefaults.filledTonalButtonColors().containerColor else product.color,
                        onClick = { onProductButtonClicked(SaleItem(product)) })
                }
            }
        }

    }
}