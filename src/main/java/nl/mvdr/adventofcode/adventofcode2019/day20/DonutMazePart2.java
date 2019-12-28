package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 20 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/20">Donut Maze</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DonutMazePart2 implements IntSolver {

    /**
     * Maximum number of layers.
     * 
     * In order to be able to use JGrapht for shortest path computation, the graph
     * needs to be finite, so there is a limit to the number of layers. If the
     * shortest path cannot be found, the value of this constant may need to be
     * increased.
     */
    private static final int MAX_LAYERS = 20;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DonutMazePart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return length of the shortest path
     */
    @Override
    public int solve(Stream<String> lines) {
        Maze maze = Maze.parse(lines.collect(Collectors.toList()));
        return maze.shortestPathInRecursiveSpace(MAX_LAYERS);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DonutMazePart2 instance = new DonutMazePart2();

        String result = instance.solve("input-day20-2019.txt");

        LOGGER.info(result);
    }
}
 