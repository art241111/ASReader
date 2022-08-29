package com.ger.av.asreader.views.openFileDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OpenFileDialogView(
    modifier: Modifier = Modifier,
    currentPosition: String,
    onOpenFile: (file: File) -> Unit,
    onBack: () -> Unit
) {
    val openFileDialog = remember { OpenFileDialogImpl(currentPosition) }
    val filesList = remember { mutableStateOf(openFileDialog.loadFileListFrom(currentPosition)) }
    val dirName = openFileDialog.directoryName.collectAsState()
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                val newList = openFileDialog.back()
                if (newList == null) {
                    onBack()
                } else {
                    filesList.value = newList
                }
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }

            Text(dirName.value)
        }
        LazyColumn() {
            if (filesList.value != null) {
                items(filesList.value!!) {
                    Card(
                        onClick = {
                            if (openFileDialog.isFile(it)) {
                                onOpenFile(openFileDialog.loadFile(it))
                            } else {
                                filesList.value = openFileDialog.loadFileListFrom(it)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(it, Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
}
