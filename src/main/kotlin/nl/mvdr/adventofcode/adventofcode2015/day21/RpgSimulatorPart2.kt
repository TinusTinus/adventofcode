package nl.mvdr.adventofcode.adventofcode2015.day21

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): Int = getAllPossibleMatchups(parseBoss(lines))
    .filter{ !it.playerWins() }
    .map(Matchup::player)
    .maxOf(Player::equipmentCost)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day21-2015.txt")
    logger.info { result }
}
