package nl.mvdr.adventofcode.adventofcode2024.day12;

import nl.mvdr.adventofcode.solver.IntSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
    	Set<Region> regions = new HashSet<>();
    	
    	// TODO fill set based on input
    	
        return regions.stream()
        		.mapToInt(Region::cost)
        		.sum();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day12-2024.txt");

        LOGGER.info(result);
    }
}
 