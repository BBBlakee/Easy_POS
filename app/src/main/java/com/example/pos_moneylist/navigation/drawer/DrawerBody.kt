package com.example.pos_moneylist.navigation.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items: List<DrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 20.sp),
    onItemClick: (DrawerItem) -> Unit,
) {

    var selectedItemId: String by remember { mutableStateOf("home") }

    LazyColumn(modifier = modifier) {
        items(items = items, key = { key -> key.id }) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                    selectedItemId = item.id
                }
                .padding(16.dp)) {
                Icon(
                    imageVector = if (selectedItemId == item.id) item.iconSelected else item.icon,
                    contentDescription = "Drawer item",
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.name, style = itemTextStyle.copy(
                        fontWeight = if (selectedItemId == item.id) FontWeight.Bold
                        else FontWeight.Normal
                    ), modifier = Modifier.weight(1f)
                )
            }
        }
    }
}