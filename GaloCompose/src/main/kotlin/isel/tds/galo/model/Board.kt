package isel.tds.galo.model

import isel.tds.galo.AppProperties


val BOARD_SIZE = AppProperties.p.getProperty("BOARD_SIZE")?.toInt() ?: 3
val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE

typealias Moves = Map<Position,Player>

sealed class Board(val moves: Moves){
    override fun equals(other: Any?) = other is Board && isEquals(other)
    override fun hashCode() = moves.hashCode() + hashAdd()
}

class BoardRun(moves: Moves=emptyMap(), val turn: Player): Board(moves)
class BoardWin(moves: Moves=emptyMap(), val winner: Player): Board(moves)
class BoardDraw(moves: Moves=emptyMap()) : Board(moves)


fun Board.isEquals(other: Board): Boolean =
    when (this) {
        is BoardRun -> other is BoardRun && turn == other.turn
        is BoardWin -> other is BoardWin && winner == other.winner
        is BoardDraw -> other is BoardDraw
    } && moves == other.moves


private fun Board.hashAdd(): Int = when (this) {
    is BoardRun -> turn.ordinal
    is BoardWin -> winner.ordinal
    is BoardDraw -> 0
}


fun Board.play(pos: Position): Board = when(this) {
    is BoardRun -> {
        require(moves[pos] == null) { "Position $pos used" }
        val moves = moves + (pos to turn)
        when {
            isWinner(pos,moves) -> BoardWin(moves, turn)
            moves.size == BOARD_CELLS -> BoardDraw(moves)
            else -> BoardRun(moves, turn.other)
        }
    }
    is BoardWin, is BoardDraw -> error("Game over")
}


private fun isWinner(pos: Position, moves: Moves): Boolean =
    moves.size >= BOARD_SIZE*Player.entries.size-1 &&
            moves.filter { it.value == moves[pos] }.keys.run {
                count{ it.row == pos.row } == BOARD_SIZE ||
                        count{ it.col == pos.col } == BOARD_SIZE ||
                        count{ it.slash } == BOARD_SIZE ||
                        count{ it.backSlash } == BOARD_SIZE
            }


fun Board(start: Player = Player.X): Board = BoardRun(emptyMap(),start)
