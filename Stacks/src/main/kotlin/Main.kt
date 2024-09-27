package org.example

import org.example.isel.tds.Stack

fun main() {
    println("Hello World!")

    val stack = Stack<String>()

    stack.push("A")
    stack.push("B")

    println(stack.top)

    stack.push("C")

    while( !stack.isEmpty()){
        println(stack.pop())
    }

}