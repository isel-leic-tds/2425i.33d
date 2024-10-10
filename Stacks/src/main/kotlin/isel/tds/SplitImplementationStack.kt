package org.example.isel.tds

interface Stack<T> : Iterable<T> {
    fun push(elem: T): Stack<T>
    fun pop(): Stack<T>
    val top: T
    fun isEmpty(): Boolean
}

private class Node<T>(val elem: T, val next: Node<T>?)

private object StackEmpty: Stack<Any> {

    override fun push(elem: Any): Stack<Any> = StackNotEmpty(Node(elem, null))

    override fun pop(): Nothing = throwEmpty()

    override val top: Nothing get() = throwEmpty()

    private fun throwEmpty(): Nothing = throw NoSuchElementException("stack empty")

    override fun isEmpty(): Boolean = true

    override fun iterator(): Iterator<Any> = object : Iterator<Any> {
        override fun hasNext() = false
        override fun next() = throwEmpty()
    }
}

private class StackNotEmpty<T>(private val head: Node<T>): Stack<T> {
    override fun push(elem: T): Stack<T> = StackNotEmpty(Node(elem, head))

    override fun pop(): Stack<T> = head.next?.let { StackNotEmpty(it) } ?: StackEmpty as Stack<T>
//    {
//        val next = head.next
//        return if (next != null) StackNotEmpty(next) else StackEmpty()
//    }

    override val top: T get() = head.elem

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var node: Node<T>? = head
        override fun hasNext() = node != null
        override fun next() = (node ?: throw NoSuchElementException())
            .also { node = it.next }.elem
    }

    override fun equals(other: Any?): Boolean {
        if (other !is StackNotEmpty<*>) return false
        var n: Node<T>? = (other as StackNotEmpty<T>).head
        for (elem in this) {
            if (n == null || elem != n.elem) return false
            n = n.next
        }
        return n == null
    }

    override fun hashCode() =
        this.fold(0) { acc, elem -> 31 * acc + elem.hashCode() }
}

fun <T> Stack(): Stack<T> = StackEmpty as Stack<T>

