package nl.mvdr.adventofcode.adventofcode2015.day02

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.ListSolver

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/2">I Was Told There Would Be No Math</a>.
 *
 * @author Martijn van de Rijdt
 */
class NoMathPart1: ListSolver<Int> {
    override fun solve(lines: List<String>): Int {
        return lines.map { line -> parse(line) }
            .sumOf { box -> box.computeWrappingPaper() }
    }
}

/**
 * Parses the string representation of a box.
 *
 * @param line textual representation of a box, for example: "2x3x4"
 * @return box
 */
private fun parse(line: String): Box {
    val (length, width, height) = line.split("x")
        .map { part -> part.toInt() }
    return Box(length, width, height)
}

private data class Box(val length: Int, val width: Int, val height: Int) {
    fun computeWrappingPaper(): Int {
        val surfaceAreas = listOf(length * width, width * height, height * length)
        val smallestSurfaceArea = surfaceAreas.min()
        return surfaceAreas.sumOf { area -> area * 2 } + smallestSurfaceArea
    }
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = NoMathPart1().solve("input-day02-2015.txt")
    logger.info { result }
}
