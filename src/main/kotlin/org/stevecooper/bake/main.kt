package org.stevecooper.bake

fun main(args: Array<String>) {
    println("Hello")
    val validInput = "hello world"
    val compiler = Compiler()
    compiler.compileString(validInput)
}
