package com.ger.av.asreader.views.openFileDialog

import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface OpenFileDialog {
    val directoryName: StateFlow<String>
    fun loadFileListFrom(directoryName: String): List<String>?
    fun loadFile(fileName: String): File
}