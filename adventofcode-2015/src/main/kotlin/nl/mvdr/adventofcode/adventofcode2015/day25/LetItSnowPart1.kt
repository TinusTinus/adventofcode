package nl.mvdr.adventofcode.adventofcode2015.day25

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

private const val PREFIX = "To continue, please consult the code grid in the manual.  Enter the code at row "
private const val INFIX = ", column "
private const val SUFFIX = "."

fun solvePart1(lines: Sequence<String>): Long {
    val text = lines.first()
    val (row, column) = text.substring(PREFIX.length, text.length - SUFFIX.length)
        .split(INFIX)
        .map(String::toInt)
    return getCode(row, column)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day25-2015.txt")
    logger.info { result }
}
