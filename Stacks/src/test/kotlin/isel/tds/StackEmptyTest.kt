package isel.tds

import org.example.isel.tds.ImmutableStack
import org.example.isel.tds.Stack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class StackEmptyTest{

    @Test
    fun `empty stack conditions`() {
        val stk = Stack<Int>()
        assertTrue(stk.isEmpty())
        assertFailsWith<NoSuchElementException> { stk.top }
        assertFailsWith<NoSuchElementException> { stk.pop() }
    }
}