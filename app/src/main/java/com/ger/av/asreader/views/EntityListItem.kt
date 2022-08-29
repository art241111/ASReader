package com.ger.av.asreader.views


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ger.av.asreader.data.Entity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EntityListItem(
    entity: Entity,
    onSelect: () -> Unit
) {
    Card (onClick = onSelect, modifier = Modifier.fillMaxWidth()) {
        Text(entity.name, Modifier.padding(10.dp))
    }
}