package nl.mvdr.adventofcode.adventofcode2025.day10

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

interface State<S : State<S>> {
    fun update(button: Button): S
    fun getInitialState(): S
    fun getPossibleStates(): Set<S>
}

fun <S: State<S>> computeFewestButtonPressesToReach(targetState: S, buttons: Set<Button>): Int {
    val initialState = targetState.getInitialState()
    val possibleStates = targetState.getPossibleStates()

    val graph = SimpleDirectedGraph<S, DefaultEdge>(DefaultEdge::class.java)
    possibleStates.forEach(graph::addVertex)
    possibleStates.forEach { sourceState -> buttons.map(sourceState::update)
        .filter(graph::containsVertex)
        .forEach { targetState -> graph.addEdge(sourceState, targetState) } }

    return DijkstraShortestPath(graph).getPath(initialState, targetState).length
}
