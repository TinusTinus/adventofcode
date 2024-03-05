package nl.mvdr.adventofcode.adventofcode2015.day02

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int =
    lines.map { line -> parse(line) }
        .sumOf { box -> box.computeRibbonLength() }

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day02-2015.txt")
    logger.info { result }
}
