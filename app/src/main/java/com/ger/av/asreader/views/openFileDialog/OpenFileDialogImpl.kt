package com.ger.av.asreader.views.openFileDialog

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class OpenFileDialogImpl(currentPosition: String) : OpenFileDialog {
    private val _directoryName = MutableStateFlow(currentPosition)
    override val directoryName: StateFlow<String> = _directoryName

    private var directoryPath = mutableListOf<String>()
    override fun loadFileListFrom(directoryName: String): List<String>? {
        val dirName = if (directoryPath.isEmpty()) directoryName else "${directoryPath.joinToString("/")}/$directoryName"
        val directory = File(dirName)
        val files = directory.listFiles()
        if (files != null) {
            directoryPath.add(directoryName)
            _directoryName.value = directoryPath.joinToString("/")
        }
        return files?.map { it.name }?.sorted()
    }

    fun isFile(directoryName: String): Boolean {
        val isFile = File("${directoryPath.joinToString("/")}/$directoryName").isFile
        return isFile
    }
    override fun loadFile(fileName: String): File {
        return File("${directoryPath.joinToString("/")}/$fileName")
    }

    fun back(): List<String>? {
        return if (directoryPath.isNotEmpty()) {
            directoryPath.removeLast()

            val dirName = directoryPath.joinToString("/")
            val directory = File(dirName)
            val files = directory.listFiles()
            if (files != null) {
                _directoryName.value = directoryPath.last()
            }
            return files?.map { it.name }?.sorted()
        } else {
            null
        }
    }
}