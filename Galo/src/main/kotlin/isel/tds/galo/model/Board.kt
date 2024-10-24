package isel.tds.galo.model

const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE
//
//class Board(
//    val cells: List<Player?> =
//        List(9) { null },
////        listOf(
////            ' ', ' ', ' ',
////            ' ', ' ', ' ',
////            ' ', ' ', ' '
////        ),
//    val turn: Player = Player.X,
//    val winner : Player? = null
//)

//fun Board.play(pos: Position): Board {
//
//    require( cells[pos.index] == null ) { "Position $pos is already used" }
//    return Board(
//        cells =cells.mapIndexed({ idx, cellContent -> if (idx == pos.index) turn else cellContent }),
//        turn = turn.other
//    )
//}
//fun Board.isWinner(player: Player):Boolean =
//    (0..2).any { col -> (0..6 step 3).all { cells[col + it] == player } } ||
//    (0..6 step 3).any { row -> (0..2).all { cells[row + it] == player } } ||
//    (0..8 step 4).all { cells[it] == player } ||
//    (2..6 step 2).all { cells[it] == player }

//
//val Board.isDraw: Boolean get() = cells.all { it!=null } && winner==null
//
//fun Board.play(pos: Position): Board {
//    check(winner == null) { "Game is over" }
//    require(cells[pos.index] == null) { "Position $pos is already used" }
//
//    val movesAfter = cells.mapIndexed { idx, c ->
//        if (idx == pos.index) turn else c
//    }
//    return Board( movesAfter, turn.other, winnerIn(pos,movesAfter))
//}
//
//private fun winnerIn(pos: Position, moves: List<Player?>): Player? {
//    val player = checkNotNull(moves[pos.index])
//    if ( moves.count { it == player } < BOARD_SIZE ) return null
//    return player.takeIf {
//        (0..<BOARD_SIZE).all {
//            moves[it + pos.row * BOARD_SIZE] == player } ||
//                (0..<BOARD_CELLS step BOARD_SIZE).all {
//                    moves[it + pos.col] == player } ||
//                pos.backSlash && (0..<BOARD_CELLS step BOARD_SIZE+1).all {
//            moves[it] == player } ||
//                pos.slash && (BOARD_SIZE-1..<BOARD_CELLS-1 step BOARD_SIZE-1).all{
//            moves[it] == player }
//    }
//}

typealias Moves =  Map<Position, Player>

sealed class Board( val moves: Moves)
class BoardRun(moves: Moves, val turn: Player): Board(moves)
class BoardWin(moves: Moves, val winner: Player): Board(moves)
class BoardDraw(moves: Moves): Board(moves)
//class BoardXyz(moves: Moves): Board(moves) // to test sealed class

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

