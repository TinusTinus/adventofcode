package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

/**
 * Representation of the puzzle input.
 */
data class Input(val vault: Vault, val initialState: State) {
    /**
     * Determines how many steps it takes to collect all (remaining) keys.
     */
    fun solve(): Int {
        val stateGraph: Graph<State, DefaultEdge> = createStateGraph()
        val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(stateGraph)
        val paths = algorithm.getPaths(initialState)
        return stateGraph.vertexSet()
            .filter { it.keyring.containsAll(vault.keys.keys) }
            .map(paths::getPath)
            .minOf { it.weight }
            .toInt()
    }

    private fun createStateGraph(): Graph<State, DefaultEdge> {
        val stateGraph: Graph<State, DefaultEdge> = SimpleDirectedWeightedGraph(DefaultEdge::class.java)
        stateGraph.addVertex(initialState)

        val states = mutableSetOf(initialState)
        while (states.isNotEmpty()) {
            val state = states.first()
            states.remove(state)

            for ((nextState, steps) in pickUpKey(state)) {
                if (stateGraph.addVertex(nextState)) {
                    states.add(nextState)
                }
                stateGraph.addEdge(state, nextState)
                stateGraph.setEdgeWeight(state, nextState, steps.toDouble())
            }
        }
        return stateGraph
    }

    /**
     * Returns a set of states reachable from a [state] by picking up a key from the vault,
     * with the associated number of steps to get to the key.
     */
    private fun pickUpKey(state: State): Map<State, Int> {
        val algorithm = vault.createShortestPathAlgorithm(state.keyring)
        val paths = algorithm.getPaths(state.position)
        val result = mutableMapOf<State, Int>()
        for (key in vault.keys.keys - state.keyring) {
            val keyPosition = vault.keys[key]!!
            val path = paths.getPath(keyPosition)
            if (path != null) {
                result[State(keyPosition, state.keyring + setOf(key))] = path.length
            }
        }
        return result
    }
}

/**
 * Parses the [lines] from the input file.
 */
fun parseInput(lines: List<String>): Input {
    var startingPoint: Point? = null
    val openPassages = mutableSetOf<Point>()
    val doors = mutableMapOf<Point, Door>()
    val keys = mutableMapOf<Key, Point>()
    for (y in lines.indices) {
        val line = lines[y]
        for (x in line.indices) {
            val point = Point(x, y)
            val c = line[x]
            if (c == '@') {
                startingPoint = point
                openPassages.add(point)
            } else if (c == '.') {
                openPassages.add(point)
            } else if (Character.isLowerCase(c)) {
                keys[Key(c)] = point
            } else if (Character.isUpperCase(c)) {
                doors[point] = Door(c)
            } else require(c == '#') { "Unexpected input: $c" }
        }
    }

    val vault = Vault(openPassages, doors, keys)
    val initialState = State(startingPoint!!)
    return Input(vault, initialState)
}
