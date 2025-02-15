package nl.mvdr.adventofcode.adventofcode2015.day03

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver
import nl.mvdr.adventofcode.point.Direction

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val directions = lines.first()
        .toCharArray()
        .map(Direction::parse)
    val visited = visitHouses(directions)
    return visited.count()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day03-2015.txt")
    logger.info { result }
}
