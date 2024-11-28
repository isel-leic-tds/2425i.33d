package isel.tds.galo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.galo.model.*
import isel.tds.galo.storage.GameSerializer
import isel.tds.galo.storage.MongoDriver
import isel.tds.galo.storage.MongoStorage
import isel.tds.galo.storage.TextFileStorage
import isel.tds.galo.view.InputName

class AppViewModel(driver: MongoDriver) {

    private val storage =
    //TextFileStorage<Name, Game>("games", GameSerializer)
    MongoStorage<Name, Game>("games", driver, GameSerializer)

    var clash by mutableStateOf(Clash(storage))   // Model state
    var viewScore by mutableStateOf(false)
    var inputName by mutableStateOf<InputName?>(null) //StartOrJoinDialog
        private set                                   //  state
    var errorMessage by mutableStateOf<String?>(null) //ErrorDialog state
        private set

    val board: Board? get() = (clash as? ClashRun)?.game?.board
    val score: Score get() = (clash as ClashRun).game.score
    val hasClash: Boolean get() = clash is ClashRun
    val sidePlayer: Player? get() = (clash as? ClashRun)?.sidePlayer
    val name: Name get() = (clash as ClashRun).id

    fun showScore() {
        viewScore = true
    }

    fun hideScore() {
        viewScore = false
    }

    fun hideError() {
        errorMessage = null
    }

    private fun exec(fx: Clash.() -> Clash): Unit =
        try {
            //throw Exception("My blow up")
            clash = clash.fx()
        } catch (e: Exception) {        // Report exceptions in ErrorDialog
            errorMessage = e.message
        }

    fun play(pos: Position) = exec { play(pos) }
    fun refresh() = exec(Clash::refresh)
    fun newBoard(): Unit = exec(Clash::newBoard)

    fun openStartDialog() {
        inputName = InputName.ForStart
    }

    fun openJoinDialog() {
        inputName = InputName.ForJoin
    }

    fun closeStartOrJoinDialog() { inputName = null }

    fun start(name: Name) {
        closeStartOrJoinDialog()
        exec { startClash(name) }
    }

    fun join(name: Name) {
        closeStartOrJoinDialog()
        exec { joinClash(name) }
    }
}