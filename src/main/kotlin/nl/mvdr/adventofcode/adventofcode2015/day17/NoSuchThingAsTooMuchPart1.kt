package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.permutations.Permutations
import kotlin.streams.asSequence

private val logger = KotlinLogging.logger{}

/**
 * A container.
 *
 * Note that this is not a data class.
 * Two different container instances with the same size are purposefully NOT considered equal.
 */
class Container(val size: Int) {
    constructor(size: String) : this(size.toInt())

    override fun toString(): String = size.toString()
}

fun solvePart1(lines: List<String>, eggnogVolume: Int = 150): Int = countWaysToFitInContainers(lines.map(::Container).toSet(), eggnogVolume)

private fun countWaysToFitInContainers(containers: Set<Container>, eggnogVolume: Int): Int = when {
    eggnogVolume < 0 -> 0
    eggnogVolume == 0 -> 1
    else -> containers.sumOf { countWaysToFitInContainers(containers - it, eggnogVolume - it.size) }
}

private fun fitInContainers(containers: List<Container>, volume: Int): Set<Container>? = when {
    volume == 0 -> setOf()
    containers.isNotEmpty() && containers.first().size <= volume -> {
        when (val tail = fitInContainers(containers.subList(1, containers.size), volume - containers.first().size)) {
            null -> null
            else -> setOf(containers.first()) union tail
        }
    }
    else -> null
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day17-2015.txt")
    logger.info { result }
}
