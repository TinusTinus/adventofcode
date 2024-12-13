package nl.mvdr.adventofcode.adventofcode2024.day03;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        return new MullItOverSolver(false).solve(lines);
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day03-2024.txt");

        LOGGER.info(result);
    }
}
 