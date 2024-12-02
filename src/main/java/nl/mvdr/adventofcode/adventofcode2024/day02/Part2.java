package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> lines) {
        return new RedNosedReportsSolver(true).solve(lines);
    }

    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day02-2024.txt");

        LOGGER.info(result);
    }
}
 