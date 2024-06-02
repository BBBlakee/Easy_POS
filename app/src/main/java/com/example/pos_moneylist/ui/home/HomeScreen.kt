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

package com.example.pos_moneylist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pos_moneylist.navigation.NavigationDestination
import com.example.pos_moneylist.ui.home.productArea.ProductArea
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptArea
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel


object DestinationHome : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    productAreaViewModel: ProductAreaViewModel,
    receiptAreaViewModel: ReceiptAreaViewModel,
    innerPadding: PaddingValues,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(innerPadding)
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Column(
            Modifier.weight(0.6f)
        ) {
            ProductArea(
                viewModel = productAreaViewModel,
                onProductButtonClicked = { saleItem ->
                    receiptAreaViewModel.addSaleItem(
                        saleItem
                    )
                })
        }

        Spacer(modifier = Modifier.size(2.dp))

        Column(
            Modifier
                .weight(0.3f)
        ) {
            Row {
                ReceiptArea(receiptAreaViewModel = receiptAreaViewModel)
            }
        }
    }
}