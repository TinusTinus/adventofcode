package nl.mvdr.adventofcode.adventofcode2021.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = synchronize(parse(lines))

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11-2021.txt")
    logger.info { result }
}
