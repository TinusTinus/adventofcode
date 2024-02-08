package nl.mvdr.adventofcode.adventofcode2015.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.ListSolver
import org.apache.commons.codec.digest.DigestUtils

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/4">The Ideal Stocking Stuffer</a>.
 *
 * @author Martijn van de Rijdt
 */
class IdealStockingStufferPart1: ListSolver<Int> {
    override fun solve(lines: List<String>): Int {
        val secretKey = lines.first()
        var result = 0
        while (!DigestUtils.md5Hex(secretKey + result).startsWith("00000")) {
            result++
        }
        return result
    }
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = IdealStockingStufferPart1().solve("input-day04-2015.txt")
    logger.info { result }
}
