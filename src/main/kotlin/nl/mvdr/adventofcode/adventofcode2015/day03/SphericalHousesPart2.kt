package nl.mvdr.adventofcode.adventofcode2015.day03

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Direction

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val directions = lines.first()
        .toCharArray()
        .map(Direction::parse)

    val santaDirections = directions.filterIndexed { index, _ ->  index % 2 == 0 } // even instructions
    val visitedBySanta = visitHouses(santaDirections)

    val roboSantaDirections = directions.filterIndexed { index, _ ->  index % 2 != 0 } // odd instructions
    val visitedByRoboSanta = visitHouses(roboSantaDirections)

    val visited = visitedBySanta union visitedByRoboSanta
    return visited.count()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day03-2015.txt")
    logger.info { result }
}
