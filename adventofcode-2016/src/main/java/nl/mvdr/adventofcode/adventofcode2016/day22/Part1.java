package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        var nodes = Node.parse(lines);
        
        return nodes.stream()
                .flatMap(a -> nodes.stream().map(b -> new NodePair(a, b)))
                .filter(NodePair::isViable)
                .count();
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day22.txt");

        LOGGER.info(result);
    }
}
