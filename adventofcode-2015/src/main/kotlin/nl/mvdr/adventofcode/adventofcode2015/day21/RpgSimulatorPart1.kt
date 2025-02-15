package nl.mvdr.adventofcode.adventofcode2015.day21

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = getAllPossibleMatchups(parseBoss(lines))
    .filter(Matchup::playerWins)
    .map(Matchup::player)
    .minOf(Player::equipmentCost)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day21-2015.txt")
    logger.info { result }
}
