package com.example.pos_moneylist.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
)