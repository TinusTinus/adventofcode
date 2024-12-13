package nl.mvdr.adventofcode.adventofcode2024.day13;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        var machines = ClawMachine.parse(lines);
    	return machines.stream()
    	        .parallel()
    	        .mapToLong(ClawMachine::calculateTokens)
    	        .sum();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day13-2024.txt");

        LOGGER.info(result);
    }
}
 