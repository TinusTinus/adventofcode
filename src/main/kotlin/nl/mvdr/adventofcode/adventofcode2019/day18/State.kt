package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge

import org.jgrapht.graph.SimpleDirectedWeightedGraph

/**
 * Representation of a possible state while traversing a maze.
 * This consists of the current [position] in the maze
 * and a [keyring] containing all the keys which have been picked up so far.
 */
data class State(val position: Point, val keyring: Set<Key> = emptySet())
