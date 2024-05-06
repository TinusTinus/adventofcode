package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph
import org.jgrapht.graph.SimpleGraph

/**
 * Representation of the puzzle input.
 */
data class Input(val vault: Vault, val initialState: State) {

    private val accessiblePassages = vault.openPassages + vault.keys.values

    private val vaultGraph: Graph<Point, DefaultEdge> = SimpleGraph(DefaultEdge::class.java)

    init {
        accessiblePassages.forEach(vaultGraph::addVertex)
        accessiblePassages.forEach { passage ->
            passage.neighbours()
                .filter(accessiblePassages::contains)
                .forEach { neighbour -> vaultGraph.addEdge(passage, neighbour) } }

        // Also add vertices for each door.
        // Do not add any edges for now: we cannot travel through a door until its key has been collected.
        vault.doors.keys.forEach(vaultGraph::addVertex)
    }

    /**
     * Returns a shortest path algorithm for traversing the vault, if the given [ownedKeys] are in the traveler's possession.
     * Note: the returned algorithm can only be used until this method is called again,
     * as this method modifies the backing graph.
     */
    private fun createShortestPathAlgorithm(ownedKeys: Set<Key>): ShortestPathAlgorithm<Point, DefaultEdge> {
        for ((doorLocation, door) in vault.doors) {
            for (neighbour in doorLocation.neighbours()) {
                vaultGraph.removeEdge(doorLocation, neighbour)
                if (ownedKeys.any { it.opens(door) } &&
                    (accessiblePassages.contains(neighbour) || vault.doors.keys.contains(neighbour))) {
                    vaultGraph.addEdge(doorLocation, neighbour)
                }
            }
        }
        return DijkstraShortestPath(vaultGraph)
    }

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
        val algorithm = createShortestPathAlgorithm(state.keyring)
        val paths = algorithm.getPaths(state.positions.first()) // TODO explore all positions, not just the first
        val result = mutableMapOf<State, Int>()
        for (key in vault.keys.keys - state.keyring) {
            val keyPosition = vault.keys[key]!!
            val path = paths.getPath(keyPosition)
            if (path != null) {
                result[State(setOf(keyPosition), state.keyring + setOf(key))] = path.length
            }
        }
        return result
    }
}

/**
 * Parses the [lines] from the input file.
 */
fun parseInput(lines: List<String>): Input {
    var startingPoints = mutableSetOf<Point>()
    val openPassages = mutableSetOf<Point>()
    val doors = mutableMapOf<Point, Door>()
    val keys = mutableMapOf<Key, Point>()
    for (y in lines.indices) {
        val line = lines[y]
        for (x in line.indices) {
            val point = Point(x, y)
            val c = line[x]
            if (c == '@') {
                startingPoints.add(point)
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
    val initialState = State(startingPoints)
    return Input(vault, initialState)
}
