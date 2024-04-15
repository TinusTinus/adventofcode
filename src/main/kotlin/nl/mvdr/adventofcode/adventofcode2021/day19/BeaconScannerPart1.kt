package nl.mvdr.adventofcode.adventofcode2021.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.adventofcode2015.day02.parse

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val scanners = parseScanners(lines.toList())

    logger.info { scanners }

    return 3 // TODO
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day19-2021.txt")
    logger.info { result }
}
