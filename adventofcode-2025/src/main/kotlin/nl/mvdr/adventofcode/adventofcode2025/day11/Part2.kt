package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long {
    val algorithm = createAlgorithm(lines)

    val svrToDacToFftToOut = when (val dacToFft = algorithm.getAllPaths("dac", "fft").size.toLong()) {
        0L -> 0L
        else -> {
            val svrToDac = algorithm.getAllPaths("svr", "dac").size.toLong()
            val fftToOut = algorithm.getAllPaths("fft", "out").size.toLong()
            svrToDac * dacToFft * fftToOut
        }
    }

    val svrToFftToDacToOut = when(val fftToDac = algorithm.getAllPaths("fft", "dac").size.toLong()) {
        0L -> 0L
        else -> {
            val svrToFft = algorithm.getAllPaths("svr", "fft").size.toLong()
            val dacToOut = algorithm.getAllPaths("dac", "out").size.toLong()
            svrToFft * fftToDac * dacToOut
        }
    }

    return svrToDacToFftToOut + svrToFftToDacToOut
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11.txt")
    logger.info { result }
}
