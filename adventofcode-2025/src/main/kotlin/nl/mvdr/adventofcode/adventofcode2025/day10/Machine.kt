package nl.mvdr.adventofcode.adventofcode2025.day10

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

data class Machine(val targetLightsState: IndicatorLightsState, val buttons: Set<Button>, val joltageRequirements: JoltageState) {

    fun computeFewestButtonPressesToInit(): Int {
        val numberOfLights = targetLightsState.lights.size
        val initialState = getInitialState(numberOfLights)
        val possibleStates = getPossibleStates(numberOfLights)

        val graph = SimpleDirectedGraph<IndicatorLightsState, DefaultEdge>(DefaultEdge::class.java)
        possibleStates.forEach(graph::addVertex)
        possibleStates.forEach { state -> buttons.forEach { button -> graph.addEdge(state, state.toggle(button)) } }

        return DijkstraShortestPath(graph).getPath(initialState, targetLightsState).length
    }

    fun computeFewestButtonPressesToRequiredJoltage(): Int {
        val numberOfJoltages = joltageRequirements.joltages.size
        val initialState = getInitialJoltageState(numberOfJoltages)
        val possibleStates = joltageRequirements.getPossibleJoltages()

        val graph = SimpleDirectedGraph<JoltageState, DefaultEdge>(DefaultEdge::class.java)
        possibleStates.forEach(graph::addVertex)
        possibleStates.forEach { sourceState -> buttons.map(sourceState::update)
            .filter(graph::containsVertex)
            .forEach { targetState -> graph.addEdge(sourceState, targetState) } }

        return DijkstraShortestPath(graph).getPath(initialState, joltageRequirements).length
    }
}

fun parseMachine(text: String): Machine {
    val parts = text.split(" ")

    val targetLightsState = parseState(parts.first())
    val buttons = parts.subList(1, parts.size - 1).map(::parseButton).toSet()
    val joltageRequirements = parseJoltageRequirements(parts.last())

    return Machine(targetLightsState, buttons, joltageRequirements)
}
