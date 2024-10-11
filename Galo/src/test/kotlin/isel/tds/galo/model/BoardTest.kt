package isel.tds.galo.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest{


        @Test
        fun `Test can play`() {
            val sut = Board(
                cells = listOf(
                    'O', 'X', 'O',
                    'X', '0', 'X',
                    'X', ' ', 'X'
                )
            )
            assertFalse(sut.canPlay(0))
            assertTrue(sut.canPlay(7))
        }

    @Test
    fun `Test play`() {
        val sut = Board(
            cells = listOf(
                'O', 'X', 'O',
                'X', '0', 'X',
                'X', ' ', 'X'
            ),
            turn = 'O'
        )
        assertTrue(sut.play(7).cells[7] == 'O')
    }

//        @Test
//        fun `Test empty Board`() {
//            val sut = Board(turn='X')
//
//            assertFalse(sut.isWinner('X'))
//            assertFalse(sut.isWinner('O'))
//        }
//
//        @Test
//        fun `Test Draw`() {
//            val sut = Board(
//                cells = listOf(
//                    'O', 'X', 'O',
//                    'X', 'O', 'X',
//                    'X', 'O', 'X'
//                )
//            )
//
//            assertFalse(sut.isWinner('X'))
//            assertFalse(sut.isWinner('O'))
//            assertTrue(sut.isDraw())
//        }
//
//        @Test
//        fun `Test backslash X win`() {
//            val sut = Board(
//                cells = listOf(
//                    'X', 'O', 'O',
//                    'O', 'X', 'X',
//                    'O', 'O', 'X'
//                )
//            )
//
//            assertTrue(sut.isWinner('X'))
//            assertFalse(sut.isWinner('O'))
//            assertFalse(sut.isDraw())
//        }
//
//        @Test
//        fun `Test backslash O win`() {
//            val sut = Board(
//                cells = listOf(
//                    'O', 'X', 'X',
//                    'O', 'O', 'X',
//                    'X', 'X', 'O'
//                )
//            )
//
//            assertFalse(sut.isWinner('X'))
//            assertTrue(sut.isWinner('O'))
//        }
}