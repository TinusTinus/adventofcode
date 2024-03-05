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
}

fun solvePart1(lines: List<String>, eggnogVolume: Int = 150): Int {
        val containers = lines.map { Container(it) }.toSet()
        return Permutations.generatePermutations(containers)
            .asSequence()
            .map { fitInContainers(it, eggnogVolume) }
            .filterNotNull()
            .distinct()
            .count()
}

private fun fitInContainers(containers: List<Container>, volume: Int): List<Container>? = when {
    volume < 0 -> null
    volume == 0 -> listOf()
    containers.isNotEmpty() && containers.first().size <= volume -> {
        when (val tail = fitInContainers(containers, volume - containers.first().size)) {
            null -> null
            else -> listOf(containers.first()) + tail
        }
    }
    else -> null
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day17-2015.txt")
    logger.info { result }
}
