package nl.mvdr.adventofcode.adventofcode2024.day04;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        return WordSearch.parse(lines.toList()).countXmasMatches();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day04-2024.txt");

        LOGGER.info(result);
    }
}
 