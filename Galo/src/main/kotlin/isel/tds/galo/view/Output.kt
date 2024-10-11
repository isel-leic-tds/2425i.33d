package isel.tds.galo.view

import isel.tds.galo.model.Board

fun Board.show() {
    for (idx in 0..6 step 3) {
        println(" ${cells.subList(idx, idx + 3).joinToString(" | ")} ")
        if (idx < 6) println("---+---+---")
    }
}