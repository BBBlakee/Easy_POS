package com.example.pos_moneylist.navigation.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontSize = 30.sp),
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = stringResource(R.string.drawer_title),
            style = textStyle,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(vertical = 30.dp)
        )
    }
}