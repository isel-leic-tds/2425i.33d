package model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SlotStateTest{

  @Test
    fun testIsWinnerOk(){
        val slotState = SlotState(1,1,1)
        assertTrue(slotState.isWinner())
    }

    @Test
    fun testNotWinner(){
        val slotState = SlotState(1,2,1)
        assertFalse(slotState.isWinner())
    }

    @Test
    fun testSlotStateWithInvalidByte(){
        assertThrows<IllegalArgumentException> {  SlotState(12,2,1)  }
    }

    @Test
    fun testRandomSlotState(){
        val slotState = SlotState.random()
        assertNotNull(slotState)
        assertTrue(slotState.slot1 in 0..9)
        assertTrue(slotState.slot2 in 0..9)
        assertTrue(slotState.slot3 in 0..9)

        val slotState2 = SlotState.random()
        assertNotNull(slotState2)
        assertTrue(slotState2.slot1 in 0..9)
        assertTrue(slotState2.slot2 in 0..9)
        assertTrue(slotState2.slot3 in 0..9)

        assertNotEquals(slotState, slotState2)
        println(slotState.slot1*100+slotState.slot2*10+slotState.slot3)
        println(slotState2.slot1*100+slotState2.slot2*10+slotState2.slot3)
        assertNotEquals(slotState.slot1*100+slotState.slot2*10+slotState.slot3, slotState2.slot1*100+slotState2.slot2*10+slotState2.slot3)

    }

 }