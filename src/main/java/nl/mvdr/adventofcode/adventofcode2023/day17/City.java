package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

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
        Graph<Crucible, DefaultWeightedEdge> graph = new SimpleDirectedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(Crucible.START);
        Set<Crucible> nextCrucibles = Crucible.START.possibleSteps().collect(Collectors.toSet());
        while (!nextCrucibles.isEmpty()) {
            nextCrucibles = nextCrucibles.stream()
                    .flatMap(crucible -> 
                        crucible.possibleSteps()
                                .filter(step -> blocks.containsKey(step.location()))
                                .filter(graph::addVertex)
                                .peek(step -> {
                                    var edge = graph.addEdge(crucible, step);
                                    var heatLoss = blocks.get(step.location()).heatLoss();
                                    graph.setEdgeWeight(edge, heatLoss);
                                }) 
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
}
