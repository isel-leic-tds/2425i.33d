package isel.tds

import isel.tds.isel.tds.tennis._01_simpleImplementationClass.Score
import isel.tds.isel.tds.tennis._01_simpleImplementationClass.initialScore
import isel.tds.isel.tds.tennis.io.readWinner

fun main() {
    var score: Score = initialScore()
    do {
        println(score.placard)
        score = score.next(readWinner())
    } while (!score.isGame)
    println("Final result")
    println(score.placard)
}


