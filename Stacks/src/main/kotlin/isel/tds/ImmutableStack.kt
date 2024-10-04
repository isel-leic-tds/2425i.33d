package org.example.isel.tds

class ImmutableStack<T> private constructor(private val head: Node<T>?) : Iterable<T> {
    constructor(): this(null)

    private class Node<T> (val elem: T, val next: Node<T>?)
        private fun firstNodeOrException() = head ?: throw NoSuchElementException("stack empty")

        val top: T get() = firstNodeOrException().elem

        fun push(elem: T) = ImmutableStack(Node(elem, head))
        fun isEmpty(): Boolean = head == null

        fun pop() = top to ImmutableStack(firstNodeOrException().next)
        fun pop2() = ImmutableStack(firstNodeOrException().next)

        fun forEach(action: (T) -> Unit) {
            var node = head
            while (node != null) {
                action(node.elem)
                node = node.next
            }
        }

    class AA{
        fun <T> hello(){
            println("Hello")
//            println("Hello $head") //Can't do this, can't access the state of the Stack
        }
    }

    inner class BB{
        fun <T> hello(){
            println("Hello $head")
        }
    }

    //1st version using Nested classes that cannot access state on the outer class
//    private class It<E>(private var node: Node<E>?) : Iterator<E> {
//        override fun hasNext() :Boolean = node != null
//        override fun next() :E = (node ?: throw NoSuchElementException())
//            .also { node = it.next }.elem
//    }
//    override fun iterator(): Iterator<T>  = It(head)

    //2nd version using inner classes that can access state on the outer class
//    private inner class It : Iterator<T> {
//        private var node = head
//
//        override fun hasNext() :Boolean = node != null
//        override fun next() :T = (node ?: throw NoSuchElementException())
//            .also { node = it.next }.elem
//    }
//    override fun iterator(): Iterator<T>  = It()

//3rd version using implicit inner class
//override fun iterator(): Iterator<T>  {
//    class It : Iterator<T> {
//        var node = head
//        override fun hasNext() = node != null
//        override fun next() = (node ?: throw NoSuchElementException())
//            .also { node = it.next }.elem
//    }
//    return It()
//}

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var node = head
        override fun hasNext() = node != null
        override fun next() = (node ?: throw NoSuchElementException())
            .also { node = it.next }.elem
    }



    override fun equals(other: Any?): Boolean {
//            if (other !is ImmutableStack<*>) return false
//            var n1 = head
//            var n2 = other.head
//            while (n1 != null && n2 != null) {
//                if (n1.elem != n2.elem) return false
//                n1 = n1.next
//                n2 = n2.next
//            }
//            return n1 == null && n2 == null
        if (other !is ImmutableStack<*>) return false
        var n = other.head
        for (elem in this) {
            if (n == null || elem != n.elem) return false
            n = n.next
        }
        return n == null
        }

//        override fun hashCode(): Int {
//            var n = head
//            var hash = 0
//            while (n != null) {
//                hash = 31 * hash + n.elem.hashCode()
//                n = n.next
//            }
//            return hash
//        }
    override fun hashCode() =
        this.fold(0) { acc, elem -> 31 * acc + elem.hashCode() }



}
