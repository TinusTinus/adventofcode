package nl.mvdr.adventofcode.adventofcode2021.day06

/**
 * Solves the puzzle, given the [lines] from the puzzle input and the number of [days] to simulate.
 */
fun solve(lines: Sequence<String>, days: Int) = School(lines).simulateDays(days).countFish()
