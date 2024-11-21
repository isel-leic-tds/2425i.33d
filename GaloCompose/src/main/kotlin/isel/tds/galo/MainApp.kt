package isel.tds.galo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import isel.tds.galo.model.*
import isel.tds.galo.ui.GridView
import isel.tds.galo.ui.StatusBar
import isel.tds.galo.ui.ScoreDialog


@Composable
@Preview
private fun FrameWindowScope.GridApp(onExit: ()->Unit) {
    var game: Game by remember { mutableStateOf(Game()) }
    var viewScore by remember { mutableStateOf(false) }
    MaterialTheme {
        MenuBar {
            Menu("Game") {
                Item("New board", onClick = { game = game.newBoard() })
                Item("Show Score", onClick = { viewScore = true })
                Item("Exit", onClick = onExit)
            }
        }
        Column() {
            GridView(game.board?.moves,
                onClickCell = { pos: Position ->
                    try {
                        game = game.play(pos)
                    } catch (ex: Exception) {
                        println(ex.message)
                    }
                }
            )
            StatusBar(game.board)
        }
        if( viewScore) ScoreDialog(game.score, onClose = { viewScore = false })
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize.Unspecified),
        title = "Tic Tac Toe"
    ) {
        GridApp(::exitApplication)
    }
}