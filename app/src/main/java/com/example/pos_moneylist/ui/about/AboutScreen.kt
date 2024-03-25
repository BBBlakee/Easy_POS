package com.example.pos_moneylist.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.navigation.NavigationDestination

object DestinationAbout : NavigationDestination {
    override val route: String = "about"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    aboutScreenViewModel: AboutScreenViewModel,
    onBackClick: () -> Unit,
) {

    val license by remember { aboutScreenViewModel.license }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(R.string.titleAboutScreen)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Go back one screen"
                    )
                }
            })
    }) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            Text(text = "About Screen")
            Icon(
                imageVector = Icons.Rounded.Info, contentDescription = "App Icon"
            )
            aboutScreenViewModel.loadLicenses(LocalContext.current)
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                item {
                    Text(text = "###################################################")
                    Text(
                        text = license.name,
                        color = Color.Green,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(text = license.link, color = Color.Blue, fontFamily = FontFamily.Monospace)
                    Text(text = license.text, fontFamily = FontFamily.Monospace)
                    Text(text = "###################################################")

                }
            }

        }
    }
}