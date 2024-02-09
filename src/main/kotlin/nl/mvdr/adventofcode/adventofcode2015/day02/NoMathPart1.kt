package nl.mvdr.adventofcode.adventofcode2015.day02

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int =
    lines.map { line -> parse(line) }
        .sumOf { box -> box.computeWrappingPaper() }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day02-2015.txt")
    logger.info { result }
}
