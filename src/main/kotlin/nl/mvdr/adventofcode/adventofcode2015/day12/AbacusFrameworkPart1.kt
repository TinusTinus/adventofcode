package nl.mvdr.adventofcode.adventofcode2015.day12

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ContainerNode
import com.fasterxml.jackson.databind.node.IntNode
import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int {
    val json = lines.first()
    val tree = ObjectMapper().readTree(json)
    return sum(tree)
}

private fun sum(node: JsonNode): Int = when(node) {
    is IntNode -> node.intValue()
    is ContainerNode<*> -> node.map(::sum).sum()
    else -> 0
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day12-2015.txt")
    logger.info { result }
}
