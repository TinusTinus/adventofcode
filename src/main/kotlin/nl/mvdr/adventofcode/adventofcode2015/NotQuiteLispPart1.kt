package nl.mvdr.adventofcode.adventofcode2015

fun main() {
    val lines = object {}.javaClass.getResourceAsStream("input-day01-2015.txt")!!.bufferedReader().readLines()
    println(solve(lines))
}

fun solve(lines: List<String>): Int = solve(lines.first())

fun solve(text: String): Int {
    return text.toCharArray()
        .map {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> throw IllegalArgumentException("Unexpected character found in input: $it")
            }
        }
        .sum()
}