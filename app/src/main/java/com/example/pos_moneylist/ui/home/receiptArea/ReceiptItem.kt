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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R

@Composable
fun ReceiptItem(
    name: String,
    counter: Int,
    price: Float,
    total: Float,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 25.sp),
    buttonSize: Dp = 25.dp,
    onMinusButtonClicked: () -> Unit,
    onPlusButtonClicked: () -> Unit,
) {

    ListItem(
        headlineContent = { Text(text = name, style = itemTextStyle) },
        supportingContent = {
            Text(
                text = String.format("$counter * %.2f € = %.2f €", price, total),
                color = Color.Gray
            )
        },
        trailingContent = {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onPlusButtonClicked,
            ) {
                Icon(
                    imageVector = Icons.TwoTone.AddCircle,
                    contentDescription = "Add sale item",
                    modifier = Modifier.size(buttonSize)
                )
            }
            Text(
                text = "${counter}x", style = itemTextStyle
            )
            IconButton(onClick = onMinusButtonClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.twotone_remove_circle),
                    contentDescription = "Remove icon",
                    modifier = Modifier.size(buttonSize),
                )
            }
        }

    },
        modifier = modifier
            .padding(vertical = 5.dp)
        //.border(BorderStroke(width = 1.dp, color = Color.Black), shape = RoundedCornerShape(15.dp))
    )
    HorizontalDivider(thickness = 1.dp)
}