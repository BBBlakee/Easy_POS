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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductButton(
    name: String,
    price: Float,
    color: Color = ButtonDefaults.filledTonalButtonColors().containerColor,
    onClick: () -> Unit = {},
) {

    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .padding(
                start = 5.dp, top = 5.dp, bottom = 5.dp, end = 5.dp
            )
            .defaultMinSize(minHeight = 100.dp)
            .fillMaxSize(),
        colors = ButtonDefaults.filledTonalButtonColors(containerColor = color),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = String.format("%.2f €", price),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}