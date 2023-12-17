package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
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
                                .peek(step -> graph.addEdge(crucible, step)) // TODO set edge weights!
                    )
                    .collect(Collectors.toSet());
        }
        
        return 0; // TODO implement
    }
}
