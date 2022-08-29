package com.ger.av.asreader

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.ger.av.asreader.data.AsFile
import com.ger.av.asreader.ui.theme.ASReaderTheme
import com.ger.av.asreader.views.DataView
import com.ger.av.asreader.views.EntityList
import com.ger.av.asreader.views.StatusBar
import com.ger.av.asreader.views.openFileDialog.OpenFileDialogView
import java.io.File

class MainActivity : ComponentActivity() {
    private val TAG = "TagOpenTxt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentPosition = Environment.getExternalStorageDirectory().path
        val intent: Intent = intent
        val action: String? = intent.action

        val text = mutableStateOf("")
        if (Intent.ACTION_VIEW == action) {
            // uri = intent.getStringExtra("URI");
            val uri2 = intent.data
            val uri = uri2?.encodedPath + "  complete: " + uri2.toString()
            text.value = uri
            println("URI: $uri")
// val file = File(Environment.getExternalStorageDirectory()+"/path/to/myfile.txt")
            // now you call whatever function your app uses
            // to consume the txt file whose location you now know
        } else {
            Log.d(TAG, "intent was something else: $action")
        }

        setContent {
            val isShowBack = remember {
                mutableStateOf(false)
            }
            val asFileDir = rememberSaveable { mutableStateOf<String?>(null) }
            val asFile = remember(asFileDir) {
                mutableStateOf<AsFile?>(
                    if (asFileDir.value != null) {
                        AsFile(
                            File(asFileDir.value!!)
                        )
                    } else null
                )
            }
            val statusBarName = rememberSaveable { mutableStateOf("Откройте файл") }
            val showMenu = rememberSaveable { mutableStateOf(true) }
            val selectedIndex = rememberSaveable { mutableStateOf(0) }

            val showOpenDialog = rememberSaveable { mutableStateOf(false) }

            ASReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (!showOpenDialog.value) {
                        Column() {
                            StatusBar(
                                onMenuClick = { showMenu.value = !showMenu.value },
                                onBack = { print("back") },
                                isShowBack = isShowBack.value,
                                name = statusBarName.value,
                                onOpenFile = {
                                    showOpenDialog.value = true
                                }
                            )

                            if (asFile.value != null) {
                                Row {
                                    if (showMenu.value) {
                                        EntityList(
                                            entities = asFile.value!!.programs,
                                            onSelect = {
                                                selectedIndex.value = it
                                            }
                                        )
                                    }
                                    if (selectedIndex.value != -1) {
                                        val index = remember(selectedIndex.value) {
                                            selectedIndex.value
                                        }
                                        statusBarName.value = asFile.value!!.programs[index].name
                                        DataView(asFile.value!!.programs[index].getStyledText())
                                    }
                                }
                            }
                        }
                    } else {
                        OpenFileDialogView(
                            currentPosition = currentPosition,
                            onOpenFile = {
                                asFile.value = AsFile(it)
                                asFileDir.value = it.absolutePath
                                showOpenDialog.value = false
                            },
                            onBack = {
                                showOpenDialog.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ASReaderTheme {
        Greeting("Android")
    }
}
