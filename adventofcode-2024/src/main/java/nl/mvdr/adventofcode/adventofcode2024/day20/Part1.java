package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final int minSavings;
    
    Part1(int minSavings) {
        this.minSavings = minSavings;
    }
    
    public Part1() {
        this(100);
    }
    
    @Override
    public long solve(Stream<String> lines) {
        return new RaceConditionSolver(2, minSavings).solve(lines);
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day20-2024.txt");

        LOGGER.info(result);
    }
}
 