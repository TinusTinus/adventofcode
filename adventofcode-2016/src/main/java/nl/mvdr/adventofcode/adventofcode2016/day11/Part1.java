package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var initialState = State.parse(lines);
        
        return 0; // TODO
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day11-2016.txt");

        LOGGER.info(result);
    }
}
