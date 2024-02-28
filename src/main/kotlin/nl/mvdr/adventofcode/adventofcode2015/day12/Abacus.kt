package nl.mvdr.adventofcode.adventofcode2015.day12

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

fun sum(lines: List<String>, filterRed: Boolean = false): Int {
    val json = lines.first()
    val tree = ObjectMapper().readTree(json)
    return sum(tree, filterRed)
}

private fun sum(node: JsonNode, filterRed: Boolean): Int = when {
    filterRed && node.isObject && node.any { it.textValue() == "red" } -> 0
    else -> node.intValue() + node.sumOf { sum(it, filterRed) }
}

