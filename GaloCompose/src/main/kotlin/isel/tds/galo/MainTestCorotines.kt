package isel.tds.galo

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun testLog(lab: String) =
    println("$lab: ${Thread.currentThread().name}")

fun main() {
    testLog("inside main")
    application {
        Window(onCloseRequest = ::exitApplication) {
            val scope = rememberCoroutineScope()
            var job by remember { mutableStateOf<Job?>(null) }
            val clickable = job == null

            Row {
                testLog("Redraw...")
                Button(enabled = clickable, onClick = {
                    println("Clicked")
                    job = scope.launch(Dispatchers.IO) {
                        testLog("Inside Coroutine...")
                        repeat(5) { print('.'); delay(1000) }
                        job = null
                    }
                } ) { Text("Click me") }
                Button(enabled = !clickable, onClick = {
                    job?.cancel().also { job = null }
                } ) { Text("Enable Click") }
            }
        }
    }
}