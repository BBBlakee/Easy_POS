package com.example.pos_moneylist.ui.about

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AndroidUriHandler
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R
import com.example.pos_moneylist.navigation.NavigationDestination

object DestinationAbout : NavigationDestination {
    override val route: String = "about"
}

@Composable
fun AboutScreen(
    aboutScreenViewModel: AboutScreenViewModel,
    innerPadding: PaddingValues,
) {

    val context: Context = LocalContext.current

    val license by remember { aboutScreenViewModel.license }
    aboutScreenViewModel.loadLicenses(context)

    val appVersion: String =
        context.packageManager.getPackageInfo(LocalContext.current.packageName, 0).versionName

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                modifier = Modifier.padding(10.dp)
            )
            Icon(
                imageVector = Icons.Rounded.Info, contentDescription = "App Icon"
            )
            Text(
                text = "Version $appVersion", fontSize = 20.sp, modifier = Modifier.padding(10.dp)
            )

            TextButton(onClick = { AndroidUriHandler(context = context).openUri(context.getString(R.string.appGithubPage)) }) {
                Text(text = stringResource(id = R.string.appGithubPage), fontSize = 20.sp)
            }

            Text(
                text = "Powered by open source", modifier = Modifier.padding(10.dp)
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                item {
                    Text(text = "###################################################")
                    Text(text = license.name, fontFamily = FontFamily.Monospace, fontSize = 25.sp)
                    TextButton(
                        onClick = { AndroidUriHandler(context = context).openUri(license.link) },

                        ) {
                        Text(text = license.link, fontFamily = FontFamily.Monospace)
                    }
                    Text(text = license.text, fontFamily = FontFamily.Monospace)
                    Text(text = "###################################################")

                }
            }

        }
    }