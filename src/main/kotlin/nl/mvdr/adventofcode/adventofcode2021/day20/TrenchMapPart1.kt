package nl.mvdr.adventofcode.adventofcode2021.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val (algorithm, image) = parse(lines.toList())
    val enhanced = algorithm.enhance(image, 2)
    return enhanced.countLightPixels()
}

private fun parse(lines: List<String>): Pair<ImageEnhancementAlgorithm, Image> {
    val algorithm = ImageEnhancementAlgorithm(lines.first())
    if (lines[1].isNotEmpty()) {
        throw IllegalArgumentException("Second line is expected to be empty, but was: " + lines[1])
    }
    val image = Image(lines.drop(2))
    return Pair(algorithm, image)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day20-2021.txt")
    logger.info { result }
}
