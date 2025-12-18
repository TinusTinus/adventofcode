package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long {
    val algorithm = createAlgorithm(lines)

    val svrToDacToFftToOut = when (val dacToFft = algorithm.countAllPaths("dac", "fft")) {
        0L -> 0L
        else -> {
            val svrToDac = algorithm.countAllPaths("svr", "dac")
            val fftToOut = algorithm.countAllPaths("fft", "out")
            svrToDac * dacToFft * fftToOut
        }
    }

    val svrToFftToDacToOut = when(val fftToDac = algorithm.countAllPaths("fft", "dac")) {
        0L -> 0L
        else -> {
            val svrToFft = algorithm.countAllPaths("svr", "fft")
            val dacToOut = algorithm.countAllPaths("dac", "out")
            svrToFft * fftToDac * dacToOut
        }
    }

    return svrToDacToFftToOut + svrToFftToDacToOut
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11.txt")
    logger.info { result }
}
