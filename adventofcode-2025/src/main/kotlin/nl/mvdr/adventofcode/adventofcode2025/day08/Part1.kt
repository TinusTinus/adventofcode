package nl.mvdr.adventofcode.adventofcode2025.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point3D
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>, numberOfPairs: Int = 1000): Int {
    val junctionBoxes = lines.map(Point3D::parse).toList()

    val pairs = junctionBoxes.flatMap { firstBox -> junctionBoxes
            .filter { secondBox -> firstBox != secondBox }
            .map { secondBox -> setOf(firstBox, secondBox) } }
        .distinct()
        .sortedBy { it.first().euclideanDistance(it.last()) }
        .take(numberOfPairs)

    val circuits = junctionBoxes.map { setOf(it) }.toMutableSet()

    for (pair in pairs) {
        val firstCircuit = circuits.find { it.contains(pair.first()) }!!
        circuits.remove(firstCircuit)

        val secondCircuit = if (firstCircuit.contains(pair.last())) {
            firstCircuit
        } else {
            circuits.find { it.contains(pair.last()) }!!
        }
        circuits.remove(secondCircuit)

        val newCircuit = firstCircuit union secondCircuit
        circuits.add(newCircuit)
    }

    return circuits.map { it.size }
        .sorted()
        .takeLast(3)
        .reduce(Int::times)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day08.txt")
    logger.info { result }
}
