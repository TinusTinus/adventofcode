package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Solution to the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMaze implements PathSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModeMaze.class);

    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                .collect(Collectors.toList());
        int depth = Integer.parseInt(lines.get(0).substring("depth: ".length()));
        Point target = Point.parse(lines.get(1).substring("target: ".length()));
        
        LOGGER.info("depth: {}, target: {}", depth, target);
        
        return null; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ModeMaze solver = new ModeMaze();
        String solution = solver.solve("input-day22-2018.txt");
        LOGGER.info(solution);
    }
}
