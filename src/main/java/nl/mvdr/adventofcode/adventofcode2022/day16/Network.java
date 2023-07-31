package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * A network of valves, pipes and tunnels.
 *
 * @author Martijn van de Rijdt
 */
record Network(Set<Valve> valves, Map<Valve, Map<Valve, Integer>> distances) {
    
    /**
     * Parses a string representation of a network.
     * 
     * @param lines puzzle input
     * @return network represented by the puzzle input
     */
    static Network parse(List<String> lines) {
        Map<Valve, Set<String>> tunnels = parseTunnels(lines);
        return new Network(tunnels.keySet(), computeDistances(tunnels.keySet(), tunnels));
    }

    /**
     * Parses a string representation of a series of valves and connecting tunnels.
     * 
     * @param lines puzzle input
     * @return tunnels connecting valves, where the values contain the names of the valves
     */
    private static Map<Valve, Set<String>> parseTunnels(List<String> lines) {
        Map<Valve, Set<String>> tunnels = new HashMap<>();
        for (String valveSpec : lines) {
            // Lines can have the following formats:
            // "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB"
            // "Valve BB has flow rate=13; tunnels lead to valves CC, AA"
            // "Valve HH has flow rate=22; tunnel leads to valve GG"
            var name = valveSpec.substring(6, 8);
            int tunnelExitsIndex;
            var semicolonIndex = valveSpec.indexOf("; tunnels lead to valves ");
            if (0 <= semicolonIndex) {
                tunnelExitsIndex = semicolonIndex + 25;
            } else {
                semicolonIndex = valveSpec.indexOf("; tunnel leads to valve ");
                tunnelExitsIndex = semicolonIndex + 24;
            }
            var flowRate = Integer.parseInt(valveSpec.substring(23, semicolonIndex));
            var tunnelExitNamesString = valveSpec.substring(tunnelExitsIndex);
            var tunnelExitNames = Stream.of(tunnelExitNamesString.split(", ")).collect(Collectors.toSet());
            tunnels.put(new Valve(name, flowRate), tunnelExitNames);
        }
        return tunnels;
    }
    
    /**
     * Precomputes the distance between each set of two vertices.
     * 
     * @param valves valves
     * @param tunnels tunnels connecting valves, where the values contain the names of the valves
     * @return shortest paths, indexed by the source valve
     */
    private static Map<Valve, Map<Valve, Integer>> computeDistances(Set<Valve> valves, Map<Valve, Set<String>> tunnels) {
        var graph = createGraph(valves, tunnels);
        ShortestPathAlgorithm<Valve, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        Map<Valve, Map<Valve, Integer>> result = new HashMap<>();
        for (Valve source : valves) {
            Map<Valve, Integer> distances = new HashMap<>();
            var paths = algorithm.getPaths(source);
            for (Valve target : valves) {
                distances.put(target, Integer.valueOf(paths.getPath(target).getLength()));
            }
            result.put(source, distances);
        }
        return result;
        
    }

    /**
     * Creates a graph with the given data.
     * 
     * @param valves valves
     * @param tunnels tunnels connecting valves, where the values contain the names of the valves
     * @return graph
     */
    private static Graph<Valve, DefaultEdge> createGraph(Set<Valve> valves, Map<Valve, Set<String>> tunnels) {
        Graph<Valve, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        valves.forEach(graph::addVertex);
        for (Entry<Valve, Set<String>> entry : tunnels.entrySet()) {
            for (String tunnelExit : entry.getValue()) {
                graph.addEdge(entry.getKey(), Valve.find(graph.vertexSet(), tunnelExit));
            }
        }
        return graph;
    }

    /**
     * @return starting location
     */
    Valve startingPoint() {
        return Valve.find(valves, "AA");
    }
    
    /**
     * Gets the distance to travel in order to get from the source to the target, along a shortest path.
     * 
     * The result is measured in the number of tunnels to traverse.
     * This is equal to the number of minutes it will take to travel the path.
     * 
     * Guaranteed to return a positive number if the valves are different.
     * 
     * @param source source valve
     * @param target target valve
     * @return distance in number of tunnels
     */
    int getDistance(Valve source, Valve target) {
        return distances.get(source).get(target).intValue();
    }
}
