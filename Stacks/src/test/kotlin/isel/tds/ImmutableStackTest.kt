package isel.tds

import org.example.isel.tds.ImmutableStack
import org.example.isel.tds.MutableStack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ImmutableStackTest{

    @Test
    fun `empty stack conditions`() {
        val stk = ImmutableStack<Int>()
        assertTrue(stk.isEmpty())
        assertFailsWith<NoSuchElementException> { stk.top }
        assertFailsWith<NoSuchElementException> { stk.pop() }
        assertFailsWith<NoSuchElementException> { stk.pop2() }
    }
    @Test fun `not empty stack conditions`() {
        val orignalStack = ImmutableStack<String>()
        val newStack = orignalStack.push("A")
        assertFalse(newStack.isEmpty())
        assertTrue(orignalStack.isEmpty())

        assertEquals("A", newStack.top)
        val finalStackPair = newStack.pop()
        assertEquals("A", finalStackPair.first)
        assertTrue(finalStackPair.second.isEmpty())
    }

    @Test fun `stack operations`() {
        val stk = ImmutableStack<Char>().push('A').push('B').push('C')
        assertEquals('C', stk.top)
        assertEquals('B', stk.pop2().top)
        val one = stk.pop2().pop2()
        assertEquals('A', one.top)
    }


//    @Test fun `equality of stacks`() {
//        val sut = MutableStack<Char>()
//        sut.push('A'); sut.push('B')
//        val sut2 = MutableStack<Char>()
//        sut2.push('A'); sut2.push('B')
//        assertEquals(sut, sut2)
//        assertEquals(sut.hashCode(), sut2.hashCode())
//        sut2.pop()
//        assertNotEquals(sut, sut2)
//        assertNotEquals(sut.hashCode(), sut2.hashCode())
//    }
}