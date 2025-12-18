package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(lines, "svr", setOf("dac", "fft"))

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11.txt")
    logger.info { result }
}
