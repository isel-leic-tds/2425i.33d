package isel.tds.galo.model

class Board(
    val cells: List<Char> =
//        List(9) { ' ' },
        listOf(
            ' ', ' ', ' ',
            ' ', ' ', ' ',
            ' ', ' ', ' '
        ),
    val turn: Char = 'X'
)

//Checks the position is free == ' '
fun Board.canPlay(pos: Int): Boolean {
    return cells[pos] == ' '
}

fun Board.play(posIdx: Int): Board {
    return Board(
        cells =cells.mapIndexed({ idx, c -> if (idx == posIdx) turn else c }),
        turn = if (turn == 'X') 'O' else 'X'
    )
}
