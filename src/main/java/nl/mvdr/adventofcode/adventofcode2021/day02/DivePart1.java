package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2021/day/2">Dive!</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DivePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivePart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var commands = Command.parse(lines);
        
        var location = Point.ORIGIN;
        for (Command command : commands) {
            location = command.execute(location);
        }
        
        return location.x() * location.y();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new DivePart1();

        var result = instance.solve("input-day02-2021.txt");

        LOGGER.info(result);
    }
}
 