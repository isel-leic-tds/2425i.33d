package isel.tds.galo.view

import isel.tds.galo.model.*

//fun Board.show() {
//    for (idx in 0..6 step 3) {
//        println(" ${cells.subList(idx, idx + 3).map { if(it==null) ' ' else it }.joinToString(" | ")} ")
//        if (idx < 6) println("---+---+---")
//    }
//    when {
//        winner!=null -> println("Winner: $winner")
//        isDraw -> println("Draw")
//        else -> println("Turn: $turn")
//    }
//}

private val separator = "---+".repeat(BOARD_SIZE-1)+"---"

fun Board.show() {
    Position.values.forEach { pos ->
        print(" ${moves[pos] ?: ' '} ")
        if (pos.col == BOARD_SIZE - 1) {
            println()
            if (pos.row < BOARD_SIZE -1) println(separator)
        } else
            print("|")
    }
    println( when(this) {
        is BoardDraw -> "Draw"
        is BoardRun -> "turn: $turn"
        is BoardWin -> "winner: $winner"
    } )
}

fun Game.show() = this.board?.show()

fun Game.showScore() {
    print("Score:")
    score.forEach { (player, value) ->
        print(" ${player ?: "Draws"}=$value ")
    }
    println()
}

fun Clash.show() {
    if (this is ClashRun) {
        println("Clash: $id Player: $sidePlayer")
        game.show()
    }
    else println("Clash not started")
}
