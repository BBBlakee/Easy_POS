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

package com.example.pos_moneylist.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pos_moneylist.R
import com.example.pos_moneylist.navigation.drawer.AppBar
import com.example.pos_moneylist.navigation.drawer.DrawerBody
import com.example.pos_moneylist.navigation.drawer.DrawerHeader
import com.example.pos_moneylist.navigation.drawer.DrawerItem
import com.example.pos_moneylist.ui.ViewModelProvider
import com.example.pos_moneylist.ui.about.AboutScreen
import com.example.pos_moneylist.ui.about.AboutScreenViewModel
import com.example.pos_moneylist.ui.about.DestinationAbout
import com.example.pos_moneylist.ui.home.DestinationHome
import com.example.pos_moneylist.ui.home.HomeScreen
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel
import com.example.pos_moneylist.ui.productListsScreen.DestinationSettings
import com.example.pos_moneylist.ui.productListsScreen.ProductListScreen
import com.example.pos_moneylist.ui.productListsScreen.ProductListsScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun MoneyListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val productAreaViewModel: ProductAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val receiptAreaViewModel: ReceiptAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val aboutScreenViewModel: AboutScreenViewModel = viewModel(factory = ViewModelProvider.Factory)
    val productListsScreenViewModel: ProductListsScreenViewModel =
        viewModel(factory = ViewModelProvider.Factory)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(items = listOf(
                    DrawerItem(
                        id = "home",
                        name = stringResource(R.string.drawer_item_home),
                        icon = Icons.Outlined.Home,
                        iconSelected = Icons.Filled.Home
                    ), DrawerItem(
                        id = "settings", name = stringResource(R.string.title_lists_screen),
                        icon = Icons.AutoMirrored.Outlined.List,
                        iconSelected = Icons.AutoMirrored.Filled.List
                    ), DrawerItem(
                        id = "about",
                        name = stringResource(R.string.title_about_screen),
                        icon = Icons.Outlined.Info,
                        iconSelected = Icons.Filled.Info
                    )
                ), onItemClick = { item ->
                    when (item.id) {
                        "home" -> navController.navigate(DestinationHome.route) {
                            launchSingleTop = true
                            restoreState = true
                        }

                        "settings" -> navController.navigate(DestinationSettings.route) {
                            launchSingleTop = true
                            restoreState = true
                        }

                        "about" -> navController.navigate(DestinationAbout.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    scope.launch { drawerState.close() }
                })
            }
        }, drawerState = drawerState, gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(topBar = {
            AppBar(onNavigationIconClick = { scope.launch { drawerState.open() } })
        }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = DestinationHome.route,
                modifier = modifier
            ) {
                composable(route = DestinationHome.route) {
                    HomeScreen(
                        productAreaViewModel = productAreaViewModel,
                        receiptAreaViewModel = receiptAreaViewModel,
                        innerPadding = innerPadding
                    )
                }

                composable(route = DestinationAbout.route) {
                    AboutScreen(
                        viewModel = aboutScreenViewModel, innerPadding = innerPadding
                    )
                }

                composable(route = DestinationSettings.route) {
                    ProductListScreen(
                        viewModel = productListsScreenViewModel, padding = innerPadding
                    )
                }
            }
        }
    }
}