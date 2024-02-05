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
class NoMathPart2: ListSolver<Int> {
    override fun solve(lines: List<String>): Int {
        return lines.map { line -> parse(line) }
            .sumOf { box -> box.computeRibbonLength() }
    }
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = NoMathPart2().solve("input-day02-2015.txt")
    logger.info { result }
}
