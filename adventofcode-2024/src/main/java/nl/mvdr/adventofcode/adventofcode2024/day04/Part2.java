package nl.mvdr.adventofcode.adventofcode2024.day04;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> lines) {
        return WordSearch.parse(lines.toList()).countCrossMasMatches();
    }

    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day04-2024.txt");

        LOGGER.info(result);
    }
}
 