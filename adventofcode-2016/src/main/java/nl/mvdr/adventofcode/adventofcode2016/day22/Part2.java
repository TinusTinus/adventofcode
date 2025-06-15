package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        var nodes = lines.skip(2)
                .map(Node::parse)
                .collect(Collectors.toSet());
        
        return 0; // TODO
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day22.txt");

        LOGGER.info(result);
    }
}
