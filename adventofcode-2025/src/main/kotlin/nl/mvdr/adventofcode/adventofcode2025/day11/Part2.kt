package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val algorithm = createAlgorithm(lines)
    return algorithm.getAllPaths("svr", "out", true, null)
        .filter { it.vertexList.contains("dac") }
        .filter { it.vertexList.contains("fft") }
        .size
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11.txt")
    logger.info { result }
}
