package com.example.pos_moneylist.ui.settingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pos_moneylist.R
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.ui.ViewModelProvider
import com.example.pos_moneylist.ui.addProductScreen.AddProductDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsScreenViewModel: SettingsScreenViewModel = viewModel(factory = ViewModelProvider.Factory),
) {

    val productList = remember { settingsScreenViewModel.productList.productList }
    var showProductList: Boolean by remember { mutableStateOf(false) }
    var showAddProductScreen: Boolean by remember { mutableStateOf(false) }

    showProductList = productList.size != 0


    Scaffold(
        modifier = Modifier.background(Color(0.0f, 0.0f, 0.0f, 0.5f)),
        floatingActionButton = {
            IconButton(onClick = { showAddProductScreen = true }) {
                Icon(
                    Icons.TwoTone.Add, contentDescription = "Add button"
                )
            }
        }) { innerPadding ->
        if (showProductList) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .background(Color(0.0f, 0.0f, 0.0f, 0.5f))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(Color.White),
                    horizontalAlignment = Alignment.Start,
                ) {
                    items(items = productList, key = { it.name }) { product: Product ->
                        Text(
                            text = product.name, fontSize = 40.sp
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = stringResource(R.string.empty_list), fontSize = 40.sp
                )
            }
        }
        if (showAddProductScreen) {
            AddProductDialog(
                onDismissRequest = { showAddProductScreen = false },
                onCancel = { showAddProductScreen = false },
                onConfirm = { product ->
                    settingsScreenViewModel.addProduct(product = product)
                    showAddProductScreen = false
                },
                onNameChange = { product -> settingsScreenViewModel.contains(product = product) })
        }
    }
}