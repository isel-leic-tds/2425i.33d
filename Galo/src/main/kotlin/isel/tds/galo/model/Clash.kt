package isel.tds.galo.model

import isel.tds.galo.storage.Storage

typealias GameStorage = Storage<Name, Game>

open class Clash( val gs: GameStorage)

class ClashRun(
    gs: GameStorage,
    val id: Name,
    val sidePlayer: Player,
    val game: Game
) : Clash(gs)

fun Clash.deleteIfIsOwner() {
    if (this is ClashRun && sidePlayer==Player.X) gs.delete(id)
}

fun Clash.startClash(name: Name): Clash {
    val game = Game()
    gs.create(name, game)
    deleteIfIsOwner()
    return ClashRun(gs, name, Player.X, game).newBoard()
}

private fun Clash.runOper(actions: ClashRun.()->Game): Clash {
    check(this is ClashRun) { "Clash not started" }
    return ClashRun(gs, id, sidePlayer, actions())
}

fun Clash.newBoard() = runOper {
    game.newBoard().also { gs.update(id,it) }
}

fun Clash.joinClash(name: Name): Clash {
    val game = gs.read(name) ?: error("Clash $name not found")
    deleteIfIsOwner()
    return ClashRun(gs, name, Player.O, game)
}

fun Clash.play(pos: Position) = runOper {
    val gameAfter = game.play(pos)
    check((game.board as BoardRun).turn == sidePlayer) {"Not your turn"}
    gameAfter.also { gs.update(id,it) }
}

fun Clash.refresh() = runOper {
    val gameAfter = gs.read(id) as Game
    check(game.board!=gameAfter.board) { "No changes" }
    gameAfter
}

