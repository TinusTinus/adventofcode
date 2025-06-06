package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        return new RedNosedReportsSolver(false).solve(lines);
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day02-2024.txt");

        LOGGER.info(result);
    }
}
 