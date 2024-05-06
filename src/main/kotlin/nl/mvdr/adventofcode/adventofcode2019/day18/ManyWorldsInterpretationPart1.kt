package nl.mvdr.adventofcode.adventofcode2019.day18;


import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.streams.asStream

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = parseInput(lines.toList()).solve()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day18-2019.txt")
    logger.info { result }
}
