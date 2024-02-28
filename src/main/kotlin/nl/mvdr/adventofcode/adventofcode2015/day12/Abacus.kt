package nl.mvdr.adventofcode.adventofcode2015.day12

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.node.ObjectNode

fun sum(lines: List<String>, filterRed: Boolean = false): Int {
    val json = lines.first()
    val tree = ObjectMapper().readTree(json)
    return sum(tree, filterRed)
}

private fun sum(node: JsonNode, filterRed: Boolean): Int = when(node) {
    is IntNode -> node.intValue()
    is ArrayNode -> node.sumOf { sum(it, filterRed) }
    is ObjectNode -> when {
        node.any { filterRed && it.textValue() == "red" } -> 0
        else -> node.sumOf { sum(it, filterRed) }
    }
    else -> 0
}
