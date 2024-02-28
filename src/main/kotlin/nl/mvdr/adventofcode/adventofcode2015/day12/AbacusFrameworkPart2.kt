package nl.mvdr.adventofcode.adventofcode2015.day12

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.*
import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): Int {
    val json = lines.first()
    val tree = ObjectMapper().readTree(json)
    return sum(tree)
}

private fun sum(node: JsonNode): Int = when(node) {
    is IntNode -> node.intValue()
    is ArrayNode -> node.map(::sum).sum()
    is ObjectNode -> when {
        node.any { it is TextNode && it.textValue() == "red" } -> 0
        else -> node.map(::sum).sum()
    }
    else -> 0
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day12-2015.txt")
    logger.info { result }
}
