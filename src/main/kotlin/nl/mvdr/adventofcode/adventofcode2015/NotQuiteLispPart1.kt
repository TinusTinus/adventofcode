package nl.mvdr.adventofcode.adventofcode2015

import java.io.File

fun main() {
    val lines = File("/input-day01-2015.txt").readLines()
    val text = lines.first()
    val result = text.toCharArray()
        .map {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> throw IllegalArgumentException("Unexpected character found in input: $it")
            }
        }
        .sum()
    println(result)
}