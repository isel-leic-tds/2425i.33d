package isel.tds.galo.storage

import isel.tds.galo.model.*

object BoardSerializer : Serializer<Board> {
    override fun serialize(data: Board): String =
        when(data) {
            is BoardRun -> "run ${data.turn}"
            is BoardWin -> "win ${data.winner}"
            is BoardDraw -> "draw"
        } + " | " +
                data.moves.entries.joinToString(" ") { (pos,plyr) ->"$pos:$plyr"}


    override fun deserialize(text: String): Board =
        text.split(" | ").let { (left, right) ->
            val moves =
                if (right.isBlank()) emptyMap()
                else right.split(" ")
                    .map { it.split(":") }
                    .associate { (index, player) ->
                        Position(index.toInt()) to player.toPlayer()
                    }
            val (type, plyr) = left.split(" ")
            when(type) {
                "run" -> BoardRun(moves, plyr.toPlayer())
                "win" -> BoardWin(moves, plyr.toPlayer())
                "draw" -> BoardDraw(moves)
                else -> error("Invalid board type: $type")
            }
        }
}
