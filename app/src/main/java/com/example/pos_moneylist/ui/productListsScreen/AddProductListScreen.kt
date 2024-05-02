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

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.pos_moneylist.R

@Composable
fun AddProductListScreen(
    onDismissRequest: () -> Unit,
    onConfirmButton: (listName: String) -> Unit,
    onDismissButton: () -> Unit,
    onValueChange: (listName: String) -> Boolean,
) {

    var listName: String by remember {
        mutableStateOf("")
    }
    var validName: Boolean by remember {
        mutableStateOf(false)
    }

    AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
        TextButton(onClick = { onConfirmButton(listName) }, enabled = validName) {
            Text(text = stringResource(id = R.string.button_confirm))
        }
    }, dismissButton = {
        TextButton(onClick = onDismissButton) {
            Text(text = stringResource(id = R.string.button_cancel))
        }
    }, title = { Text(text = stringResource(R.string.add_list_title)) },
        text = {
            OutlinedTextField(value = listName, onValueChange = {
                listName = it
                validName = onValueChange(listName) and listName.isNotEmpty()
            }, label = {
                Text(
                    text = stringResource(
                        id = R.string.add_product_name
                    )
                )
            }, isError = !validName)
        })
}