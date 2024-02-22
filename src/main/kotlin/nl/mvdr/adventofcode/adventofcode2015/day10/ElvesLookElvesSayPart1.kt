package nl.mvdr.adventofcode.adventofcode2015.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int = lookAndSay(lines.first(), 40).length

fun lookAndSay(input: String, times: Int): String = when {
        times < 0 -> throw IllegalArgumentException("times was $times, must be positive")
        times == 0 -> input
        else -> lookAndSay(lookAndSay(input), times - 1)
    }

fun lookAndSay(input: String) = "3" // TODO

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day10-2015.txt")
    logger.info { result }
}
