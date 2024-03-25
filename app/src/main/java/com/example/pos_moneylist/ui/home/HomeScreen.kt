package com.example.pos_moneylist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.Info
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
import com.example.pos_moneylist.R
import com.example.pos_moneylist.navigation.NavigationDestination
import com.example.pos_moneylist.ui.home.productArea.ProductArea
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptArea
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel
import com.example.pos_moneylist.ui.settingsScreen.SettingsScreen


object DestinationHome : NavigationDestination {
    override val route: String = "home"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    productAreaViewModel: ProductAreaViewModel,
    receiptAreaViewModel: ReceiptAreaViewModel,
    onInfoClick: () -> Unit,
) {

    var showSettingsScreen: Boolean by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.topAppBar_title)) },
            actions = {
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
                IconButton(onClick = onInfoClick) {
                        Icon(
                            Icons.TwoTone.Info,
                            modifier = Modifier.size(48.dp),
                            contentDescription = "Open about menu"
                        )
                }
            })
    }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            Row {
                Column(
                    Modifier.weight(0.6f)
                ) {
                    ProductArea(
                        productAreaViewModel = productAreaViewModel,
                        onProductButtonClicked = { saleItem ->
                            receiptAreaViewModel.addSaleItem(
                                saleItem
                            )
                        })
                }

                Column(
                    Modifier.weight(0.3f),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        ReceiptArea(receiptAreaViewModel = receiptAreaViewModel)
                    }
                }

            }
            if (showSettingsScreen) {
                SettingsScreen()
            }
        }
    }
}