package nl.mvdr.adventofcode.adventofcode2015.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val brightness = mutableMapOf<Point, Int>()
    for (instruction in lines.map(::Instruction)) {
        instruction.applyPart2(brightness)
    }
    return brightness.values.sum()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day06-2015.txt")
    logger.info { result }
}
