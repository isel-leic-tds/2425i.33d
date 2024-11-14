import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var text2 by remember { mutableStateOf("Hello, World2!") }
    log("App")
    MaterialTheme {
        Column {
            Row {
                Button(
                    onClick = {
                        text = "Hello, Desktop!${System.currentTimeMillis()}"
                    }, modifier = Modifier.size(500.dp)
                ) {
                    Text(text)
                }
            }
            Row {
                Button(onClick = {
                    text2 = "Hello, Desktop2!"
                }) {
                    Text(text2)
                }
            }
        }
    }
    log("App End")
}

fun main() {
    log("main")
    application {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            this.window.title = "Compose for Desktop"
            App()
        }
        log("application End")
    }
    log("main End")
}


fun log(label: String) {
    println("$label thread=${Thread.currentThread().name}")
}
