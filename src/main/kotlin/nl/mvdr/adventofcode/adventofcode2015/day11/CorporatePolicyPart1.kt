package nl.mvdr.adventofcode.adventofcode2015.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): String = findNextPassword(lines.first())

fun findNextPassword(currentPassword: String): String = "derp" // TODO implement

fun increment(password: String): String = "derp" // TODO implement

fun isValidPassword(password: String) = true // TODO implement

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day11-2015.txt")
    logger.info { result }
}
