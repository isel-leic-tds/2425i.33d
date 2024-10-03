package org.example

import org.example.isel.tds.MutableStack

fun main() {
    println("Hello World!")

    val stack = MutableStack<String>()

    stack.push("A")
    stack.push("B")

    println(stack.top)

    stack.push("C")

    while( !stack.isEmpty()){
        println(stack.pop())
    }

}