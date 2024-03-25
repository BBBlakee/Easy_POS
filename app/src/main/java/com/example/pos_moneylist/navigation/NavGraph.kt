package com.example.pos_moneylist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pos_moneylist.ui.ViewModelProvider
import com.example.pos_moneylist.ui.about.AboutScreen
import com.example.pos_moneylist.ui.about.AboutScreenViewModel
import com.example.pos_moneylist.ui.about.DestinationAbout
import com.example.pos_moneylist.ui.home.DestinationHome
import com.example.pos_moneylist.ui.home.HomeScreen
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel

@Composable
fun MoneyListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val productAreaViewModel: ProductAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val receiptAreaViewModel: ReceiptAreaViewModel = viewModel(factory = ViewModelProvider.Factory)
    val aboutScreenViewModel: AboutScreenViewModel = viewModel(factory = ViewModelProvider.Factory)

    NavHost(
        navController = navController, startDestination = DestinationHome.route, modifier = modifier
    ) {
        composable(route = DestinationHome.route) {
            HomeScreen(
                productAreaViewModel = productAreaViewModel,
                receiptAreaViewModel = receiptAreaViewModel,
                onInfoClick = { navController.navigate(DestinationAbout.route) }
            )
        }
        composable(route = DestinationAbout.route) {
            AboutScreen(
                aboutScreenViewModel = aboutScreenViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}