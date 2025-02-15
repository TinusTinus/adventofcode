package nl.mvdr.adventofcode.adventofcode2024.day23;

import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends LanPartySolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public String solve(Graph<String, DefaultEdge> network) {
        return new BronKerboschCliqueFinder<>(network)
                .maximumIterator()
                .next()
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
 