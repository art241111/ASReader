package com.ger.av.asreader.data

import java.io.File

data class AsFile(
    val file: File
) {
    private val _programs = mutableListOf<Program>()
    val programs: List<Program> = _programs

    init {
        val content = file.readText()
        val section = content.split(".END")
        section.forEach {
            when {
                it.contains(".PROGRAM") -> {
                    _programs.add(Program.createByText(it))
                }
            }
        }
    }
}