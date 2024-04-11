package com.example.pos_moneylist.ui.home.receiptArea

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_moneylist.R

@Composable
fun ReceiptItem(
    name: String,
    counter: Int,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 25.sp),
    tonalElevation: Dp = 3.dp,
    buttonSize: Dp = 25.dp,
    onMinusButtonClicked: () -> Unit,
    onPlusButtonClicked: () -> Unit,
) {

    ListItem(headlineContent = { Text(text = name, style = itemTextStyle) }, trailingContent = {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onPlusButtonClicked,
            ) {
                Icon(
                    imageVector = Icons.TwoTone.AddCircle,
                    contentDescription = "Add sale item",
                    modifier = Modifier.size(buttonSize)
                )
            }
            Text(
                text = "${counter}x", style = itemTextStyle
            )
            IconButton(onClick = onMinusButtonClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.twotone_remove_circle),
                    contentDescription = "Remove icon",
                    modifier = Modifier.size(buttonSize),
                )
            }
        }

    },
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(15.dp))
    )
}