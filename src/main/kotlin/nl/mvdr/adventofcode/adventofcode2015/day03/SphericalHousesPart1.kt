package nl.mvdr.adventofcode.adventofcode2015.day03

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.ListSolver
import nl.mvdr.adventofcode.point.Direction

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/3">Perfectly Spherical Houses in a Vacuum</a>.
 *
 * @author Martijn van de Rijdt
 */
class SphericalHousesPart1: ListSolver<Int> {
    override fun solve(lines: List<String>): Int {
        val directions = lines.first()
            .toCharArray()
            .map(Direction::parse)
        val visited = visitHouses(directions)
        return visited.count()
    }
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = SphericalHousesPart1().solve("input-day03-2015.txt")
    logger.info { result }
}
