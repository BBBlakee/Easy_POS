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

package com.example.pos_moneylist.ui.home.receiptArea

import CurrencyAmountInputVisualTransformation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R

@Composable
fun CheckoutDialog(
    total: Float,
    onDismissRequest: () -> Unit,
    onClickPayButton: () -> Unit,
    onClickDismissButton: () -> Unit,
) {

    var changeInput: String by remember {
        mutableStateOf("")
    }
    var changeOutput: Float by remember {
        mutableFloatStateOf(0.00f)
    }

    AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
        TextButton(onClick = onClickPayButton) {
            Text(
                text = stringResource(id = R.string.checkout_button),
                color = Color(0xFF3C8B40)
            )
        }
    }, dismissButton = {
        TextButton(onClick = onClickDismissButton) {
            Text(text = stringResource(id = R.string.button_cancel), color = Color.Red)
        }
    }, title = { Text(text = stringResource(R.string.change)) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(value = changeInput, onValueChange = { newValue ->
                    changeInput = newValue
                    changeOutput = ((newValue.toFloat() / 100.00f) - total)
                }, visualTransformation = CurrencyAmountInputVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    label = { Text(text = stringResource(R.string.given)) })
                Spacer(modifier = Modifier.size(30.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.change) + ":",
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = String.format("%.2f EUR", changeOutput),
                        fontSize = 25.sp
                    )
                }

            }
        })
}