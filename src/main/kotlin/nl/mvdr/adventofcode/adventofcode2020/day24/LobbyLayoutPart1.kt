package nl.mvdr.adventofcode.adventofcode2020.day24


import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.map(::Path)
    .map(Path::move)
    .groupingBy { it }
    .eachCount()
    .count { it.value % 2 == 1 }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2020.txt")
    logger.info { result }
}
