package isel.tds.galo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import isel.tds.galo.model.Player
import isel.tds.galo.ui.PlayerPreview
import isel.tds.galo.ui.PlayerView


fun main() = application {
    Window(title="Test PlayerView", onCloseRequest = ::exitApplication) {
        PlayerApp()
    }
}

@Composable
fun PlayerApp(){
    var player by remember { mutableStateOf(Player.X) }

    MaterialTheme {
        Column (
            modifier= Modifier.background(Color.Black).fillMaxHeight()
        ){
            PlayerView(100.dp, player)
            Button(onClick = { player = player.other }) {
                Text("Change Player")
            }
        }
    }
}