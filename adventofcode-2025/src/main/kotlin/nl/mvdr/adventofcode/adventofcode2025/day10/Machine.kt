package nl.mvdr.adventofcode.adventofcode2025.day10

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

data class Machine(val targetLightsState: IndicatorLightsState, val buttons: Set<Button>) {

    fun computeFewestButtonPressesToInit(): Int {
        val numberOfLights = targetLightsState.lights.size
        val initialState = IndicatorLightsState(generateSequence { false }.take(numberOfLights).toList())
        val possibleStates = getPossibleStates(numberOfLights)

        val graph = SimpleDirectedGraph<IndicatorLightsState, DefaultEdge>(DefaultEdge::class.java)
        possibleStates.forEach(graph::addVertex)
        possibleStates.forEach { state -> buttons.forEach { button -> graph.addEdge(state, state.toggle(button)) } }

        return DijkstraShortestPath(graph).getPath(initialState, targetLightsState).length
    }
}

fun parseMachine(text: String): Machine {
    val parts = text.split(" ")

    val targetLightsState = parseState(parts.first())
    val buttons = parts.subList(1, parts.size - 1).map(::parseButton).toSet()

    return Machine(targetLightsState, buttons)
}
