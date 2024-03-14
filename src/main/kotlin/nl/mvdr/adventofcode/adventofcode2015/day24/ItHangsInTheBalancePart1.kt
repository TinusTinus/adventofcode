package nl.mvdr.adventofcode.adventofcode2015.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val packages = lines.map(String::toInt).toSet()

    val weightPerGroup = packages.sum() / 3

    return 3 // TODO
}

private fun quantumEntanglement(group: Set<Int>) = group.reduce { package0, package1 -> package0 * package1 }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2015.txt")
    logger.info { result }
}
