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
public class DonutMazePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonutMazePart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the sum of the alignment parameters
     */
    @Override
    public int solve(Stream<String> lines) {
        Maze maze = Maze.parse(lines.collect(Collectors.toList()));
        
        return 0; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DonutMazePart1 instance = new DonutMazePart1();

        String result = instance.solve("input-day20-2019.txt");

        LOGGER.info(result);
    }
}
 