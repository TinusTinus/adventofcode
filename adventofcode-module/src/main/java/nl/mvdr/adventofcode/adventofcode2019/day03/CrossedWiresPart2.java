package nl.mvdr.adventofcode.adventofcode2019.day03;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/3">Crossed Wires</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CrossedWiresPart2 extends CrossedWires {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrossedWiresPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the fewest combined steps to the given point
     */
    @Override
    int computeDistance(Point point, List<Point> wire0, List<Point> wire1) {
        return wire0.indexOf(point) + wire1.indexOf(point);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CrossedWiresPart2 instance = new CrossedWiresPart2();

        String result = instance.solve("input-day03-2019.txt");

        LOGGER.info(result);
    }
}
