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

package com.example.pos_moneylist.navigation.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.pos_moneylist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    }, navigationIcon = {
        IconButton(onClick = onNavigationIconClick) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle drawer")
        }
    }, colors = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
    )
    )
}

