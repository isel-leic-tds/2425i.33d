package isel.tds.tennis._02_oo

import isel.tds.isel.tds.tennis.Player
import isel.tds.isel.tds.tennis._02_oo.initialScore
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ScoreTest_02_oo {

  @Test
  fun `Initial Score conditions`() {
   val score = initialScore()
   assertEquals("0 - 0", score.placard)
   assertFalse { score.isGame }
  }

  @Test
  fun `Initial Score next`() {
   val score = initialScore().next(Player.A)
   assertEquals("15 - 0", score.placard)
   assertFalse { score.isGame }
  }

  @Test
  fun `Middle Score next`() {
   var score = initialScore()

   score = score.next(Player.A).next(Player.B)
   assertEquals("15 - 15", score.placard)
   assertFalse { score.isGame }

   score = score.next(Player.B)
   assertEquals("15 - 30", score.placard)
   assertFalse { score.isGame }

   score = score.next(Player.B)
   assertEquals("15 - 40", score.placard)
   assertFalse { score.isGame }
  }

  @Test
  fun `Deuce Score condition`() {
   val score = initialScore()
    .next(Player.A).next(Player.A).next(Player.A)
    .next(Player.B).next(Player.B).next(Player.B)
   assertEquals("Deuce", score.placard)
   assertFalse { score.isGame }
  }

  @Test
  fun `Advantage Score condition`() {
   val score =
    initialScore()
     .next(Player.A).next(Player.A).next(Player.A)
     .next(Player.B).next(Player.B).next(Player.B)
     .next(Player.B)
   assertEquals("Advantage B", score.placard)
   assertFalse { score.isGame }
  }


  @Test
  fun `Game without advantage Score condition`() {
   val score =
    initialScore()
     .next(Player.A).next(Player.A).next(Player.A)
     .next(Player.B).next(Player.B)
     .next(Player.A)
   assertEquals("Game A", score.placard)
   assertTrue { score.isGame }
  }


  @Test
  fun `Deuce after Advantage Score condition`() {
   val score =
    initialScore()
     .next(Player.A).next(Player.A).next(Player.A)
     .next(Player.B).next(Player.B).next(Player.B)
     .next(Player.B) //Advantage B
     .next(Player.A) //Deuce
   assertEquals("Deuce", score.placard)
   assertFalse { score.isGame }
  }

  @Test
  fun `Game with advantage Score condition`() {
   val score =
    initialScore()
     .next(Player.A).next(Player.A).next(Player.A)
     .next(Player.B).next(Player.B).next(Player.B)
     .next(Player.A).next(Player.A)
   assertEquals("Game A", score.placard)
   assertTrue { score.isGame }
  }

 }