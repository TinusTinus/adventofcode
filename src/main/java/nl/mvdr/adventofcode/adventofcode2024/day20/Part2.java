package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    private final int minSavings;
    
    Part2(int minSavings) {
        this.minSavings = minSavings;
    }
    
    public Part2() {
        this(100);
    }
    
    @Override
    public long solve(Stream<String> lines) {
        return new RaceConditionSolver(20, minSavings).solve(lines);
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day20-2024.txt");

        LOGGER.info(result);
    }
}
 