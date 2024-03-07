package com.example.pos_moneylist.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pos_moneylist.R
import com.example.pos_moneylist.ui.ViewModelProvider
import com.example.pos_moneylist.ui.home.HomeDestination
import com.example.pos_moneylist.ui.home.HomeScreen
import com.example.pos_moneylist.ui.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.receiptArea.ReceiptAreaViewModel
import com.example.pos_moneylist.ui.settingsScreen.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val productAreaViewModel: ProductAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val receiptAreaViewModel: ReceiptAreaViewModel = viewModel(factory = ViewModelProvider.Factory)

    var showSettingsScreen: Boolean by remember { mutableStateOf(false) }

    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            Scaffold(topBar = {
                TopAppBar(title = { Text(stringResource(R.string.topAppBar_title)) }, actions = {
                    IconButton(onClick = { showSettingsScreen = !showSettingsScreen }) {
                        if (!showSettingsScreen) {
                            Icon(
                                Icons.TwoTone.Settings,
                                modifier = Modifier.size(48.dp),
                                contentDescription = "Edit product list button"
                            )
                        } else {
                            Icon(
                                Icons.TwoTone.Close,
                                modifier = Modifier.size(48.dp),
                                contentDescription = "Close button"
                            )
                        }
                    }
                })
            }) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    HomeScreen(
                        productAreaViewModel = productAreaViewModel,
                        receiptAreaViewModel = receiptAreaViewModel,
                    )
                    if (showSettingsScreen) {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}