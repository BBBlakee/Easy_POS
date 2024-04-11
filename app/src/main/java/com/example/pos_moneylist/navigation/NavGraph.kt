package com.example.pos_moneylist.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
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
import com.example.pos_moneylist.ui.settingsScreen.DestinationSettings
import com.example.pos_moneylist.ui.settingsScreen.SettingsScreen
import com.example.pos_moneylist.ui.settingsScreen.SettingsScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun MoneyListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val productAreaViewModel: ProductAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val receiptAreaViewModel: ReceiptAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val aboutScreenViewModel: AboutScreenViewModel = viewModel(factory = ViewModelProvider.Factory)
    val settingsScreenViewModel: SettingsScreenViewModel =
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
                        id = "settings",
                        name = stringResource(R.string.title_settings_screen),
                        icon = Icons.Outlined.Settings,
                        iconSelected = Icons.Filled.Settings
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
                        aboutScreenViewModel = aboutScreenViewModel, innerPadding = innerPadding
                    )
                }

                composable(route = DestinationSettings.route) {
                    SettingsScreen(
                        settingsScreenViewModel = settingsScreenViewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}