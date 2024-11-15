package isel.tds.galo.model

@JvmInline
value class Position private constructor(val index: Int) {
    val row: Int get() = index / BOARD_SIZE  // row in (0..<BOARD_SIZE)
    val col: Int get() = index % BOARD_SIZE  // col in (0..<BOARD_SIZE)
    val backSlash get() = row == col         // Is in principal diagonal \
    val slash get() = row+col == BOARD_SIZE-1// Is in secondary diagonal /


    override fun toString() = "$index"
    companion object {
        val values = List(BOARD_CELLS) { Position(it) } // All positions
        operator fun invoke(index: Int): Position =
            values[index] // Can throw IndexOutOfBounds
    }
}


fun Position(row: Int, col: Int): Position {
    require(row in 0..<BOARD_SIZE && col in 0..<BOARD_SIZE)
    return Position.values[row * BOARD_SIZE + col]
}


fun Int.toPositionOrNull(): Position? =
    if (this in Position.values.indices) Position(this) else null

