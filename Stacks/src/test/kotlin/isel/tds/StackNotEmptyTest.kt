package isel.tds

import org.example.isel.tds.Stack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StackNotEmptyTest{

    @Test
    fun `not empty stack conditions`() {
        val orignalStack = Stack<String>()
        val newStack = orignalStack.push("A")
        assertFalse(newStack.isEmpty())
        assertTrue(orignalStack.isEmpty())

        assertEquals("A", newStack.top)
        assertTrue(newStack.pop().isEmpty())
    }

    @Test fun `stack operations`() {
        val stk = Stack<Char>().push('A').push('B').push('C')
        assertEquals('C', stk.top)
        assertEquals('B', stk.pop().top)
        val one = stk.pop().pop()
        assertEquals('A', one.top)
    }

}