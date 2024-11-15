package isel.tds.galo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import isel.tds.galo.model.*
import isel.tds.galo.ui.GridView


@Composable
@Preview
private fun GridApp() {
    var board: Board by remember { mutableStateOf(BoardRun(turn= Player.X)) }
    MaterialTheme {
        GridView(board,
            onClickCell = { pos: Position ->
            try {
                board = board.play(pos)
            }catch (ex: Exception) {
                println(ex.message)
            }
        }
        )
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize.Unspecified)
    ) {
        GridApp()
    }
}