package nl.mvdr.adventofcode.adventofcode2020.day03;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/3">Toboggan Trajectory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TobogganTrajectoryPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TobogganTrajectoryPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of trees encountered
     */
    @Override
    public long solve(Stream<String> lines) {
        Map map = Map.parse(lines);
        
        return IntStream.range(0, map.getHeight())
            .mapToObj(i -> new Point(3 * i, i))
            .filter(map::containsTree)
            .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TobogganTrajectoryPart1 instance = new TobogganTrajectoryPart1();

        String result = instance.solve("input-day03-2020.txt");

        LOGGER.info(result);
    }
}
 