package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 6 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/6">Universal Orbit Map</a>.
 *
 * @author Martijn van de Rijdt
 */
public class UniversalOrbitMapPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalOrbitMapPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return minimum number of orbital transfers
     */
    @Override
    public int solve(Stream<String> lines) {
        Map<String, CelestialObject> objects = CelestialObject.parse(lines);
        
        Graph<CelestialObject, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        objects.values().forEach(graph::addVertex);
        objects.values().stream()
                .filter(object -> object.getOrbitedObject().isPresent())
                .forEach(object -> graph.addEdge(object, object.getOrbitedObject().orElseThrow()));
        
        ShortestPathAlgorithm<CelestialObject, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        CelestialObject source = objects.get("YOU").getOrbitedObject().get();
        CelestialObject sink = objects.get("SAN").getOrbitedObject().get();
        return shortestPathAlgorithm.getPath(source, sink).getLength();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        UniversalOrbitMapPart2 instance = new UniversalOrbitMapPart2();

        String result = instance.solve("input-day06-2019.txt");

        LOGGER.info(result);
    }
}
