package nl.mvdr.adventofcode.adventofcode2025.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.CharacterConsumer
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val rolls = parseInput(lines)
    return rolls.count { roll -> roll.neighboursIncludingDiagonals().count(rolls::contains) < 4 }
}

private fun parseInput(lines: Sequence<String>): Set<Point> {
    val result = mutableSetOf<Point>();
    Point.parse2DMap(lines.toList(), CharacterConsumer { point, character -> if (character == '@') { result.add(point) } })
    return result
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day04.txt")
    logger.info { result }
}
