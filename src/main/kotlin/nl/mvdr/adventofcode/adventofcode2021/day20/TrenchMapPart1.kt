package nl.mvdr.adventofcode.adventofcode2021.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toList()

    val algorithm = ImageEnhancementAlgorithm(lines.first())
    if (lines[1].isNotEmpty()) {
        throw IllegalArgumentException("Second line is expected to be empty, but was: " + lines[1])
    }
    val image = Image(lines.drop(2))

    val enhanced = algorithm.enhance(image, 2)
    return enhanced.countLightPixels()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day20-2021.txt")
    logger.info { result }
}
