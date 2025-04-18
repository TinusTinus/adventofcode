package nl.mvdr.adventofcode.adventofcode2020.day03;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/3">Toboggan Trajectory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TobogganTrajectoryPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TobogganTrajectoryPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the number of trees encountered on each of the listed slopes
     */
    @Override
    public long solve(Stream<String> lines) {
        Map map = Map.parse(lines);
        
        List<Point> slopes = List.of(
                new Point(1, 1),
                new Point(3, 1),
                new Point(5, 1),
                new Point(7, 1),
                new Point(1, 2));
        
        return slopes.stream()
                .mapToLong(map::countTrees)
                .reduce((i, j) -> i * j)
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TobogganTrajectoryPart2 instance = new TobogganTrajectoryPart2();

        String result = instance.solve("input-day03-2020.txt");

        LOGGER.info(result);
    }
}
 