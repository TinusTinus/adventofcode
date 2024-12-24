package nl.mvdr.adventofcode.adventofcode2024.day23;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends LanPartySolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    /// Finds the largest interconnected set of computers containing all of the computers in the given set.
    ///
    /// @param network the entire network
    /// @param computers nonempty set of interconnected computers
    /// @return the largest possible set of interconnected computers containing all of the given ones
    private static Set<String> largestInterconnectedSet(Graph<String, DefaultEdge> network, Set<String> interconnectedComputers) {
        return Graphs.neighborSetOf(network, interconnectedComputers.iterator().next())
                .stream()
                .filter(Predicate.not(interconnectedComputers::contains))
                .filter(computer -> interconnectedComputers.stream().allMatch(interconnectedComputer -> network.containsEdge(computer, interconnectedComputer)))
                .map(computer -> Stream.concat(Stream.of(computer), interconnectedComputers.stream()).collect(Collectors.toSet()))
                .map(newInterconnectedComputers -> largestInterconnectedSet(network, newInterconnectedComputers))
                .max(Comparator.comparing(Set::size))
                .orElse(interconnectedComputers);
    }
    
    @Override
    public String solve(Graph<String, DefaultEdge> network) {
        return network.vertexSet()
                .parallelStream()
                .map(computer -> largestInterconnectedSet(network, Set.of(computer)))
                .max(Comparator.comparing(Set::size))
                .orElseThrow()
                .stream()
                .sorted()
                .collect(Collectors.joining(","));
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day23-2024.txt");

        LOGGER.info(result);
    }
}
 