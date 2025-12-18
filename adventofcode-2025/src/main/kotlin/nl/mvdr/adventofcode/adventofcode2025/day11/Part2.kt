package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long {
    val algorithm = createAlgorithm(lines)

    // Observation: there are no paths from dac to fft.
    // This is true both in the example and in my actual input.
    // We only need to consider paths that visit svr -> fft -> dac -> out

    val svrToFft = algorithm.getAllPaths("svr", "fft").size.toLong()
    val fftToDac = algorithm.getAllPaths("fft", "dac").size.toLong()
    val dacToOut = algorithm.getAllPaths("dac", "out").size.toLong()
    return svrToFft * fftToDac * dacToOut
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11.txt")
    logger.info { result }
}
