package com.example.pos_moneylist.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pos_moneylist.ui.about.AboutScreenViewModel
import com.example.pos_moneylist.ui.home.productArea.ProductAreaViewModel
import com.example.pos_moneylist.ui.home.receiptArea.ReceiptAreaViewModel
import com.example.pos_moneylist.ui.settingsScreen.SettingsScreenViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            ProductAreaViewModel()
        }

        initializer {
            ReceiptAreaViewModel()
        }

        initializer {
            SettingsScreenViewModel()
        }
        initializer {
            AboutScreenViewModel()
        }
    }
}