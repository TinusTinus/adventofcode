package nl.mvdr.adventofcode.adventofcode2021.day15

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(expand(parseRisks(lines)))

private fun expand(risks: Map<Point, Int>): Map<Point, Int> {
    return risks // TODO
}


fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day15-2021.txt")
    logger.info { result }
}
