package com.ger.av.asreader.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatusBar(
    onMenuClick: () -> Unit,
    onBack: () -> Unit,
    isShowBack: Boolean,
    name: String,
    onOpenFile: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onMenuClick) {
            Icon(Icons.Default.Menu, "Menu")
        }
        if (isShowBack) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, "Back")
            }
        }
        Text(name)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onOpenFile, Modifier.padding(end = 10.dp)) {
            Text("Открыть файл")
        }
    }
}
