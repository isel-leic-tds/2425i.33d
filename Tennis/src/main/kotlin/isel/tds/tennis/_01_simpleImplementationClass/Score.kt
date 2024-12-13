package isel.tds.isel.tds.tennis._01_simpleImplementationClass

import isel.tds.isel.tds.tennis.Player
import isel.tds.isel.tds.tennis.Points

class Score(val pointsA: Points, val pointsB: Points){

    val placard: String
        get() = when{
            pointsA == Points.FORTY && pointsB == Points.FORTY -> "Deuce"
            pointsA == Points.ADVANTAGE -> "Advantage A"
            pointsB == Points.ADVANTAGE -> "Advantage B"
            pointsA == Points.GAME -> "Game A"
            pointsB == Points.GAME -> "Game B"
            else -> "${pointsA.value} - ${pointsB.value}"
        }
    val isGame: Boolean get() = pointsA == Points.GAME || pointsB == Points.GAME

    fun next(winner: Player): Score = when {
        winner == Player.A && pointsA == Points.FORTY && pointsB == Points.ADVANTAGE -> Score(Points.FORTY, Points.FORTY)
        winner == Player.B && pointsB == Points.FORTY && pointsA == Points.ADVANTAGE -> Score(Points.FORTY, Points.FORTY)
        winner == Player.A && pointsA == Points.FORTY && pointsB != Points.FORTY -> Score(Points.GAME, pointsB)
        winner == Player.B && pointsB == Points.FORTY && pointsA != Points.FORTY -> Score(pointsA, Points.GAME)
        else ->
            if (winner == Player.A) Score(pointsA.next(), pointsB)
            else Score(pointsA, pointsB.next())
    }
}


fun initialScore(): Score = Score(Points.LOVE, Points.LOVE)

