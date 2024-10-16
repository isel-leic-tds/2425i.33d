package isel.tds.galo.model

@JvmInline
value class Position private constructor(val index: Int) {

    companion object{
        val values = List(BOARD_CELLS){Position(it)}

        operator fun invoke(index: Int): Position =
            values[index] // Can throw IndexOutOfBounds
    }
}

fun Position(row: Int, col: Int): Position {
    require(row in 0 ..< BOARD_SIZE && col in 0 ..< BOARD_SIZE)
    return Position.values[row * BOARD_SIZE + col]
}

fun Int.toPositionOrNull(): Position? =
    if (this in Position.values.indices) Position(this) else null