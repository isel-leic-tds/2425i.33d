package org.example.isel.tds

//1st version
class MutableStackWithImmutableList<T> {
    private var elems = emptyList<T>()

    val top: T get() = elems.last()

    fun push(elem: T) {
        elems = elems + elem
    }

    fun isEmpty(): Boolean = elems.isEmpty()

    fun pop(): T {
        val elem = elems.last()
        elems = elems.dropLast(1)
        return elem
    }

    override fun equals(other: Any?): Boolean {
        return other is MutableStackWithImmutableList<*> && elems == other.elems;
    }

    override fun hashCode(): Int {
        return elems.hashCode()
    }
}

//2nd version
//class MutableStackWithMutableList<T> {
//    private val elems = mutableListOf<T>()
//
//    val top: T get() = elems.last()
//    fun push(elem: T) { elems.add(elem)}
//    fun isEmpty(): Boolean = elems.isEmpty()
//
//    fun pop(): T {
//        return elems.removeLast()
//    }
//
//    override fun equals(other: Any?): Boolean {
//        return other is MutableStackWithMutableList<*> && elems == other.elems;
//    }
//
//    override fun hashCode(): Int {
//        return elems.hashCode()
//    }
//}

class Stack<T> {
    private class Node<T> (val elem: T, val next: Node<T>?)
    private var head: Node<T>? = null

    private fun firstNodeOrException() = head ?: throw NoSuchElementException("stack empty")

    val top: T get() = firstNodeOrException().elem

     fun push(elem: T) {
        head = Node( elem, head)
    }
    fun isEmpty(): Boolean = head == null

//    fun pop(): T = firstNode().apply { head = next }.elem
    fun pop(): T = firstNodeOrException().also { head = it.next }.elem
//    {
//        val elem = firstNode().elem
//        head = head!!.next
//        return elem
//    }

    override fun equals(other: Any?): Boolean {
        if (other !is Stack<*>) return false
        var n1 = head
        var n2 = other.head
        while (n1 != null && n2 != null) {
            if (n1.elem != n2.elem) return false
            n1 = n1.next
            n2 = n2.next
        }
        return n1 == null && n2 == null
    }

    override fun hashCode(): Int {
        var n = head
        var hash = 0
        while (n != null) {
            hash = 31 * hash + n.elem.hashCode()
            n = n.next
        }
        return hash
    }
}