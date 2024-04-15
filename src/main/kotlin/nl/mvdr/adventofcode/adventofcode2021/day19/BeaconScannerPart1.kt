package nl.mvdr.adventofcode.adventofcode2021.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val scanners = parseScanners(lines.toList())
    logger.debug { "Scanners: $scanners" }

    return 3 // TODO
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day19-2021.txt")
    logger.info { result }
}
