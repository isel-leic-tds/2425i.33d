package isel.tds.isel.tds.tennis._02_oo

import isel.tds.isel.tds.tennis.Player
import isel.tds.isel.tds.tennis.Points
import isel.tds.isel.tds.tennis.Points.*

fun initialScore(): Score = ByPoints(Points.LOVE, Points.LOVE)

interface Score{
    fun next(winner: Player): Score
    val isGame: Boolean get() = false
    val placard: String
}

private class Game(val winner: Player): Score {
    override fun next(winner: Player): Score {
        error("game over")
    }
    override val placard: String get() = "Game ${winner.name}"
    override val isGame: Boolean get() = true
}

private class Advantage(val playerWithTheAdvantage: Player) : Score {
    override fun next(winnerOfTheLastPoint: Player): Score = when {
        winnerOfTheLastPoint == playerWithTheAdvantage -> Game(playerWithTheAdvantage)
        else -> Deuce()
    }
    override val placard: String get() = "Advantage ${playerWithTheAdvantage.name}"
}

private class Deuce : Score {
    override fun next(winner: Player): Score = Advantage(winner)
    override val placard: String get() = "Deuce"
}

private class ByPoints(val pointsA: Points, val pointsB: Points) : Score {
    override fun next(winner: Player): Score = when {
        winner == Player.A && pointsA == FORTY && pointsB != FORTY -> Game(Player.A)
        winner == Player.B && pointsB == FORTY && pointsA != FORTY -> Game(Player.B)
        winner == Player.A && pointsA == THIRTY && pointsB == FORTY -> Deuce()
        winner == Player.B && pointsB == THIRTY && pointsA == FORTY -> Deuce()
        else ->
            if (winner == Player.A) ByPoints(pointsA.next(), pointsB)
            else ByPoints(pointsA, pointsB.next())
    }
    override val placard: String
        get() = "${pointsA.value} - ${pointsB.value}"

}