package com.example.pos_moneylist.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pos_moneylist.ui.ViewModelProvider
import com.example.pos_moneylist.ui.home.HomeDestination
import com.example.pos_moneylist.ui.home.HomeScreen
import com.example.pos_moneylist.ui.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.receiptArea.ReceiptAreaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val productAreaViewModel: ProductAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val receiptAreaViewModel: ReceiptAreaViewModel = viewModel(factory = ViewModelProvider.Factory)

    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                productAreaViewModel = productAreaViewModel,
                receiptAreaViewModel = receiptAreaViewModel
            )
        }
    }
}