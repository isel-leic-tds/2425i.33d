package org.example.isel.tds

class ImmutableStack<T> private constructor(private val head: Node<T>?) {
    constructor(): this(null)

    private class Node<T> (val elem: T, val next: Node<T>?)
        private fun firstNodeOrException() = head ?: throw NoSuchElementException("stack empty")

        val top: T get() = firstNodeOrException().elem

        fun push(elem: T) = ImmutableStack(Node(elem, head))
        fun isEmpty(): Boolean = head == null

        fun pop() = top to ImmutableStack(firstNodeOrException().next)
        fun pop2() = ImmutableStack(firstNodeOrException().next)


//    override fun equals(other: Any?): Boolean {
//            if (other !is MutableStack<*>) return false
//            var n1 = head
//            var n2 = other.head
//            while (n1 != null && n2 != null) {
//                if (n1.elem != n2.elem) return false
//                n1 = n1.next
//                n2 = n2.next
//            }
//            return n1 == null && n2 == null
//        }
//
//        override fun hashCode(): Int {
//            var n = head
//            var hash = 0
//            while (n != null) {
//                hash = 31 * hash + n.elem.hashCode()
//                n = n.next
//            }
//            return hash
//        }


}
