package com.ger.av.asreader.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun DataView(
    data: AnnotatedString
) {
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        item {
            Text(data)
        }
    }
}
