package com.ger.av.asreader.data

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

class Program(override val name: String, override val text: String) : Entity {
    companion object {
        fun createByText(text: String): Program {
            val name = text.substringAfter(".PROGRAM").substringBefore("\n").substringBefore("(")
            val _text = "${text.trim()}\n.END"
            return Program(
                name = name,
                text = _text
            )
        }
    }

    fun getStyledText(): AnnotatedString {
        val lines = text.split("\n")
        val wordsInLine: List<List<String>> = lines.map { it.split(" ") }
        val commands = Commands.getNames()
        val text: AnnotatedString = buildAnnotatedString {
            wordsInLine.forEach {
                it.forEach { word ->
                    when {
                        commands.contains(word) -> {
                            pushStringAnnotation(tag = "command", annotation = "word")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("$word ")
                            }
                            pop()
                        }

                        else -> {
                            append("$word ")
                        }
                    }
                }
                append("\n")
            }
        }
        return text
    }
}
