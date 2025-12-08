package nl.mvdr.adventofcode.adventofcode2025.day08

import nl.mvdr.adventofcode.point.Point3D

fun parseJunctionBoxes(lines: Sequence<String>) = lines.map(Point3D::parse).toList()

fun findPairs(junctionBoxes: List<Point3D>, numberOfPairs: Int) =
    junctionBoxes.flatMap { firstBox -> junctionBoxes
        .filter { secondBox -> firstBox != secondBox }
        .map { secondBox -> setOf(firstBox, secondBox) } }
        .distinct()
        .sortedBy { it.first().euclideanDistance(it.last()) }
        .take(numberOfPairs)

fun findCircuits(junctionBoxes: List<Point3D>, pairs: List<Set<Point3D>>): Set<Set<Point3D?>> {
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

    return circuits
}
