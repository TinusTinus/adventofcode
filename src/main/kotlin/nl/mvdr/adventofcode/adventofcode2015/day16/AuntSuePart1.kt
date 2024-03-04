package nl.mvdr.adventofcode.adventofcode2015.day16

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

private val compounds = mapOf(
    Pair(Compound.CHILDREN, 3),
    Pair(Compound.CATS, 7),
    Pair(Compound.SAMOYEDS, 2),
    Pair(Compound.POMERANIANS, 3),
    Pair(Compound.AKITAS, 0),
    Pair(Compound.VIZSLAS, 0),
    Pair(Compound.GOLDFISH, 5),
    Pair(Compound.TREES, 3),
    Pair(Compound.CARS, 2),
    Pair(Compound.PERFUMES, 1)
)

fun solvePart1(lines: List<String>) = lines.map(::parseSue).find { it.matchesCompounds(compounds) }!!.number

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day16-2015.txt")
    logger.info { result }
}
