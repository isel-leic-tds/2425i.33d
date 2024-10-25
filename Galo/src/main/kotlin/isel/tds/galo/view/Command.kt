package isel.tds.galo.view

import isel.tds.galo.model.*
import isel.tds.galo.storage.Storage

class Command(
    val isToFinish: Boolean = false,
    val execute: (args: List<String>, game: Game)->Game = { _, g:Game -> g}
)

private val Play = Command() { args, game ->
        requireNotNull(game.board) { "Game not started" }
        val arg = requireNotNull(args.firstOrNull()) {"Missing position"}
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull()) { "Invalid position $arg" }

        game.play(pos)
    }

//object NewCommand : Command() {
//    override fun execute(args: List<String>, game: Game): Game {
//        return game.newBoard()
//    }
//}
//
//object Exit : Command(isToFinish = true) {
//    override fun execute(args: List<String>, game: Game): Game {
//        return Game()
//    }
//}
//
//object Show : Command() {
//    override fun execute(args: List<String>, game: Game): Game
//        = game.also { it.showScore() }
//}

private fun storageCommand(exec: (Game, String) -> Game) =
    Command() { args, game ->
        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
        require(name.isNotBlank() && name.all { it.isLetter() }) {
            "Invalid name $name"
        }
        exec(game, name)
    }

typealias GameStorage = Storage<String, Game>
fun getCommands(st: GameStorage): Map<String, Command>  =
    mapOf(
        "PLAY" to Play,
        "NEW" to Command() { _, game -> game.newBoard() },
        "SHOW" to Command() { _, game -> game.also { it.showScore() } },
        "EXIT" to Command(isToFinish = true),
        "SAVE" to storageCommand { game, name -> game.also {
            try { st.create(name, game) }
            catch (e: Exception) { error("Game $name already exists") }
        } },
        "LOAD" to storageCommand { _, name ->
            checkNotNull(st.read(name)) { "Game $name not found" }
        }
    )

