package isel.tds.galo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import isel.tds.galo.storage.MongoDriver
import isel.tds.galo.view.*
import isel.tds.galo.viewmodel.AppViewModel


@Composable
@Preview
private fun FrameWindowScope.GridApp(driver: MongoDriver, onExit: () -> Unit) {

    val scope = rememberCoroutineScope()
    var vm: AppViewModel = remember { AppViewModel(driver, scope) }

    MaterialTheme {
        MenuBar {
            Menu("Game") {
                Item("Start game", onClick = vm::openStartDialog)
                Item("Join game", onClick = vm::openJoinDialog)
                Item("New board", onClick = vm::newBoard)
                Item("Refresh", enabled = vm.hasClash, onClick = vm::refresh)
                Item("Show Score", onClick = vm::showScore)
                Item("Exit", onClick = onExit)
            }
        }
        Column() {
            GridView(vm.board?.moves, onClickCell = vm::play)
            StatusBar(vm.board, vm.sidePlayer)
        }
        if (vm.viewScore) ScoreDialog(vm.score, vm.name,onClose = vm::hideScore)

        vm.inputName?.let {
            StartOrJoinDialog(
            type = it,
                onCancel = vm::closeStartOrJoinDialog,
                onAction= if (it== InputName.ForStart) vm::start else vm::join
        ) }
        vm.errorMessage?.let { ErrorDialog(it, onClose = vm::hideError) }
        if (vm.isWaiting) waitingIndicator()
    }
}

fun main() = MongoDriver("galo").use { driver ->
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = WindowState(size = DpSize.Unspecified),
            title = "Tic Tac Toe"
        ) {
            GridApp(driver, ::exitApplication)
        }
    }
}