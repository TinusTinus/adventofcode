package nl.mvdr.adventofcode.adventofcode2015.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = Program(lines).execute().registers[Register.B]!!

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day22-2015.txt")
    logger.info { result }
}
