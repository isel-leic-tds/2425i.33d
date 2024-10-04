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


    @Test fun testForEachCustomFun(){
        val threeElemStack = ImmutableStack<Int>().push(1).push(2).push(3)
        var sum = 0
        threeElemStack.forEach { sum += it }
        println("sum = $sum, top = ${threeElemStack.top}") // Output: sum = 6, top = 3

        assertEquals(6, sum)
        assertEquals(3, threeElemStack.top)

    }

    @Test fun testForEach(){
        val stk = ImmutableStack<Int>().push(1).push(2).push(3)
        var sum = 0
        for(e in stk) {
            sum += e
        }
        println("sum = $sum, top = ${stk.top}") // Output: sum = 6, top = 3

        assertEquals(6, sum)
        assertEquals(3, stk.top)

        assertEquals(6, stk.sum())
    }

    @Test fun `equality of stacks`() {
        var sut = ImmutableStack<Char>()
        sut = sut.push('A').push('B')
        var sut2 = ImmutableStack<Char>()
        sut2 = sut2.push('A').push('B')
        assertEquals(sut, sut2)
        assertEquals(sut.hashCode(), sut2.hashCode())
        sut2 = sut2.pop2()
        assertNotEquals(sut, sut2)
        assertNotEquals(sut.hashCode(), sut2.hashCode())

    }
    @Test
    fun testNestedClass(){
        val myAa = ImmutableStack.AA()
        myAa.hello<String>()

        val s = ImmutableStack<String>()
        val myBb = s.BB()
        myBb.hello<String>()

    }

}