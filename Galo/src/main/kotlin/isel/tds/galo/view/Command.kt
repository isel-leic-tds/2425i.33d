package isel.tds.galo.view

import isel.tds.galo.model.Board
import isel.tds.galo.model.play

abstract class Command(val isToFinish: Boolean = false) {
    abstract fun execute(args: List<String>, board: Board?): Board
}

object Play : Command() {
    override fun execute(args: List<String>, board: Board?): Board {
        requireNotNull(board) { "Game not started" }
        val arg = requireNotNull(args.firstOrNull()) {"Missing position"}
        val pos = requireNotNull(arg.toIntOrNull()) { "Invalid pos $arg" }

        return board.play(pos)
    }
}

object NewCommand : Command() {
    override fun execute(args: List<String>, board: Board?): Board {
        return Board()
    }
}

object Exit : Command(isToFinish = true) {
    override fun execute(args: List<String>, board: Board?): Board {
        return Board()
    }
}

fun getCommands(): Map<String, Command>  =
    mapOf(
        "PLAY" to Play,
        "NEW" to NewCommand,
        "EXIT" to Exit
    )

