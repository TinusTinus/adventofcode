package nl.mvdr.adventofcode.adventofcode2015.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    var burningLights = setOf<Point>()
    for (instruction in lines.map(::Instruction)) {
        burningLights = instruction.applyPart1(burningLights)
    }
    return burningLights.size
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day06-2015.txt")
    logger.info { result }
}
