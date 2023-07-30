package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A network of valves, pipes and tunnels.
 *
 * @author Martijn van de Rijdt
 */
record Network(Map<Valve, Set<Valve>> tunnels) {
    
    /**
     * Parses a string representation of a network.
     * 
     * @param lines puzzle input
     * @return network represented by the puzzle input
     */
    static Network parse(List<String> lines) {
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
        return toNetwork(tunnels);
    }

    /**
     * Helper method to create a network based on the given tunnels.
     * 
     * @param tunnels tunnels, where tunnel exits are represented by the name of the target valve
     * @return network
     */
    private static Network toNetwork(Map<Valve, Set<String>> tunnels) {
        Map<Valve, Set<Valve>> result = new HashMap<>();
        for (Entry<Valve, Set<String>> entry : tunnels.entrySet()) {
            var tunnelExits = entry.getValue()
                    .stream()
                    .map(name -> tunnels.keySet()
                            .stream()
                            .filter(valve -> valve.name().equals(name))
                            .findFirst()
                            .orElseThrow())
                    .collect(Collectors.toSet());
            result.put(entry.getKey(), tunnelExits);
        }
        return new Network(result);
    }
    
    /**
     * @return starting location
     */
    Valve startingPoint() {
        return tunnels.keySet()
                .stream()
                .filter(valve -> "AA".equals(valve.name()))
                .findFirst()
                .orElseThrow();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Network: \n");
        for (Entry<Valve, Set<Valve>> entry : tunnels.entrySet()) {
            builder.append("Valve ");
            builder.append(entry.getKey().name());
            builder.append(" has flow rate=");
            builder.append(entry.getKey().flowRate());
            builder.append("; ");
            if (entry.getValue().size() == 1) {
                builder.append("tunnel leads to valve ");
            } else {
                builder.append("tunnels lead to valves ");
            }
            builder.append(entry.getValue()
                    .stream()
                    .map(Valve::name)
                    .collect(Collectors.joining(", ")));
            builder.append("\n");
        }
        return builder.toString();
    }
}
