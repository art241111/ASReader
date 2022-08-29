package com.ger.av.asreader.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ger.av.asreader.data.Entity

@Composable
fun EntityList(
    entities: List<Entity>,
    onSelect: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.width(150.dp)) {
        item {
            Text(
                text = "Список программ",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
        itemsIndexed(entities) { index, it ->
            EntityListItem(it) {
                onSelect(index)
            }
        }
    }
}
