package isel.tds.galo.view

import isel.tds.galo.model.*

class Command(
    val argsSyntax: String = "",
    val isToFinish: Boolean = false,
    val execute: (args: List<String>, clash: Clash)->Clash = { _, c:Clash -> c}
)

private val Play = Command() { args, clash ->
        require(clash is ClashRun) { "Game not started" }
        val arg = requireNotNull(args.firstOrNull()) {"Missing position"}
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull()) { "Invalid position $arg" }

        clash.play(pos)
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

//private fun storageCommand(exec: (Game, String) -> Game) =
//    Command() { args, game ->
//        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
//        require(name.isNotBlank() && name.all { it.isLetter() }) {
//            "Invalid name $name"
//        }
//        exec(game, name)
//    }

private fun beginCommand(exec: Clash.(Name) -> Clash) =
    Command("<name>")
//    { args ->
    { args, clash ->
        val word = requireNotNull(args.firstOrNull()) {"Missing name"}
//        exec(Name(word))
        clash.exec(Name(word))
    }


//typealias GameStorage = Storage<String, Game>
//fun getCommands(st: GameStorage): Map<String, Command>  =
fun getCommands(): Map<String, Command>  =
    mapOf(
        "PLAY" to Play,
        "NEW" to Command() { _, clash -> clash.newBoard() },
        "SHOW" to Command() { _, clash -> clash.also {
            (it as? ClashRun)?.game?.showScore()
        } },
        "EXIT" to Command(isToFinish = true),
//        "SAVE" to storageCommand { game, name -> game.also {
//            try { st.create(name, game) }
//            catch (e: Exception) { error("Game $name already exists") }
//        } },
//        "LOAD" to storageCommand { _, name ->
//            checkNotNull(st.read(name)) { "Game $name not found" }
//        }
        "START" to beginCommand { name -> this.startClash(name) },
        "JOIN" to beginCommand { name -> joinClash(name) },
        "REFRESH" to Command { _, clash -> clash.refresh() },

    )

