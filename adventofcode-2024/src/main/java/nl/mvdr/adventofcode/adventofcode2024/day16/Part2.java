package nl.mvdr.adventofcode.adventofcode2024.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> lines) {
        return Maze.parse(lines.toList()).countBestPathTiles();
    }

    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day16-2024.txt");

        LOGGER.info(result);
    }
}
 