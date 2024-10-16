package isel.tds.galo.view

import isel.tds.galo.model.Board
import isel.tds.galo.model.Player
import isel.tds.galo.model.isWinner

fun Board.show() {
    for (idx in 0..6 step 3) {
        println(" ${cells.subList(idx, idx + 3).map { if(it==null) ' ' else it }.joinToString(" | ")} ")
        if (idx < 6) println("---+---+---")
    }
    when{
        isWinner(Player.X) -> println("Winner: X")
        isWinner(Player.O) -> println("Winner: O")
        cells.all { it != null } -> println("Draw")
        else -> println("Turn: $turn")
    }
}