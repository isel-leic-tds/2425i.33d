package isel.tds.galo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import isel.tds.galo.model.*
import isel.tds.galo.view.GridView
import isel.tds.galo.view.StatusBar
import isel.tds.galo.view.ScoreDialog
import isel.tds.galo.viewmodel.AppViewModel


//@Composable
//@Preview
//private fun FrameWindowScope.GridApp(onExit: ()->Unit) {
//
//    var vm: AppViewModel = remember { AppViewModel() }
//
//    MaterialTheme {
//        MenuBar {
//            Menu("Game") {
//                Item("New board", onClick = vm::newBoard)
//                Item("Show Score", onClick = vm::showScore)
//                Item("Exit", onClick = onExit)
//            }
//        }
//        Column() {
//            GridView(vm.game.board?.moves, onClickCell = vm::play)
//            StatusBar(vm.game.board)
//        }
//        if( vm.viewScore) ScoreDialog(vm.game.score, onClose = vm::hideScore)
//    }
//}
//
//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        state = WindowState(size = DpSize.Unspecified),
//        title = "Tic Tac Toe"
//    ) {
//        GridApp(::exitApplication)
//    }
//}