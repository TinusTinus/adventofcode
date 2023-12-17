package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import nl.mvdr.adventofcode.point.Point;

/**
 * The city through which lava needs to be moved.
 *
 * @author Martijn van de Rijdt
 */
record City(Map<Point, Block> blocks) {
    /**
     * Parses the string representation of a city.
     * 
     * @param lines lines from the puzzle input
     * @return map
     */
    static City parse(List<String> lines) {
        var blocks = Point.parse2DMap(lines, Block::parse);
        return new City(blocks);
    }
    
    /**
     * @return minimum heat loss of a path from start to finish
     */
    int computeMinimumHeatLoss() {
        // Build a graph out of all possible states of the crucible.
        Graph<Crucible, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(Crucible.START);
        Set<Crucible> latestCrucibles = Set.of(Crucible.START);
        while (!latestCrucibles.isEmpty()) {
            latestCrucibles = latestCrucibles.stream()
                    .flatMap(crucible -> 
                        crucible.possibleSteps(this)
                                .filter(nextCrucible -> addVertexAndEdge(graph, nextCrucible, crucible)) 
                    )
                    .collect(Collectors.toSet());
        }
        
        var goal = new Point(Point.maxX(blocks.keySet()), Point.maxY(blocks.keySet()));
        
        ShortestPathAlgorithm<Crucible, DefaultWeightedEdge> algorithm = new DijkstraShortestPath<>(graph);
        var paths = algorithm.getPaths(Crucible.START);
        return graph.vertexSet()
               .stream()
               .filter(crucible -> crucible.location().equals(goal))
               .mapToDouble(paths::getWeight)
               .mapToInt(d -> (int)d)
               .min()
               .orElseThrow();
    }

    /**
     * Adds a new vertext and a new edge to the graph.
     * 
     * @param graph graph
     * @param newVertex vertext to add
     * @param previousVertex previous vertex, that is: the source vertex for the new edge
     * @return whether the vertext was newly added to the graph
     */
    private boolean addVertexAndEdge(Graph<Crucible, DefaultWeightedEdge> graph, Crucible newVertex,
            Crucible previousVertex) {
        var result = graph.addVertex(newVertex);
        var edge = graph.addEdge(previousVertex, newVertex);
        var heatLoss = blocks.get(newVertex.location()).heatLoss();
        graph.setEdgeWeight(edge, heatLoss);
        return result;
    }
}
