package com.example.pos_moneylist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.navigation.NavigationDestination
import com.example.pos_moneylist.ui.home.productArea.ProductArea
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptArea
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel


object DestinationHome : NavigationDestination {
    override val route: String = "home"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    productAreaViewModel: ProductAreaViewModel,
    receiptAreaViewModel: ReceiptAreaViewModel,
    onInfoClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {

    Scaffold(
        contentWindowInsets = WindowInsets(
            left = 10.dp,
            right = 10.dp,
            top = 10.dp,
            bottom = 10.dp,
        ), topBar = {
            TopAppBar(title = { Text(stringResource(R.string.topAppBar_title)) }, actions = {
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        Icons.Outlined.Settings,
                        modifier = Modifier.size(48.dp),
                        contentDescription = "Open settings menu"
                    )
                }
                IconButton(onClick = onInfoClick) {
                    Icon(
                        Icons.Outlined.Info,
                        modifier = Modifier.size(48.dp),
                        contentDescription = "Open about menu"
                    )
                }
            })
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                Modifier.weight(0.6f)
            ) {
                ProductArea(productAreaViewModel = productAreaViewModel,
                    onProductButtonClicked = { saleItem ->
                        receiptAreaViewModel.addSaleItem(
                            saleItem
                        )
                    })
            }

            Column(
                Modifier
                    .weight(0.3f)
                    .padding(start = 10.dp),
            ) {
                Row {
                    ReceiptArea(receiptAreaViewModel = receiptAreaViewModel)
                }
            }
        }


    }
}