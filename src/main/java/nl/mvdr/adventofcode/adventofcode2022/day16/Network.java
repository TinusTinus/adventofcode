package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * A network of valves, pipes and tunnels.
 *
 * @author Martijn van de Rijdt
 */
record Network(Graph<Valve, DefaultEdge> graph) {
    
    /**
     * Parses a string representation of a network.
     * 
     * @param lines puzzle input
     * @return network represented by the puzzle input
     */
    static Network parse(List<String> lines) {
        Graph<Valve, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
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
            Valve valve = new Valve(name, flowRate);
            graph.addVertex(valve);
            var tunnelExitNamesString = valveSpec.substring(tunnelExitsIndex);
            var tunnelExitNames = Stream.of(tunnelExitNamesString.split(", ")).collect(Collectors.toSet());
            tunnels.put(valve, tunnelExitNames);
        }
        for (Entry<Valve, Set<String>> entry : tunnels.entrySet()) {
            for (String tunnelExit : entry.getValue()) {
                graph.addEdge(entry.getKey(), Valve.find(graph.vertexSet(), tunnelExit));
            }
        }
        return new Network(graph);
    }

    /**
     * @return starting location
     */
    Valve startingPoint() {
        return Valve.find(graph.vertexSet(), "AA");
    }
}
