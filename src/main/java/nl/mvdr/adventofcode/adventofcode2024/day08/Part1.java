package nl.mvdr.adventofcode.adventofcode2024.day08;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        return CityMap.parse(lines.toList()).countAntinodes();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day08-2024.txt");

        LOGGER.info(result);
    }
}
 