package nl.mvdr.adventofcode.adventofcode2024.day23;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part1 extends LanPartySolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public Long solve(Graph<String, DefaultEdge> network) {
        var result = network.vertexSet()
                .stream()
                .filter(firstComputer -> firstComputer.startsWith("t"))
                .flatMap(firstComputer -> Graphs.neighborListOf(network, firstComputer)
                        .stream()
                        .flatMap(secondComputer -> Graphs.neighborListOf(network, firstComputer)
                                .stream()
                                .filter(thirdComputer -> network.containsEdge(secondComputer, thirdComputer))
                                .map(thirdComputer -> Set.of(firstComputer, secondComputer, thirdComputer))))
                .distinct()
                .count();
        
        return Long.valueOf(result);
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day23-2024.txt");

        LOGGER.info(result);
    }
}
 