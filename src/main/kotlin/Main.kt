package org.example

fun main() {
    print("Cinema:\n  ")
    var i = 1
    repeat(8) { print("${i++} ") }
    println()
    i = 1
    repeat(7) { println("${i++} ${"S ".repeat(8)}")}

}