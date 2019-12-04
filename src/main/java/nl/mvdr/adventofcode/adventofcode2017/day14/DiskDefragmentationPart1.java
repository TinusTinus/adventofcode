package nl.mvdr.adventofcode.adventofcode2017.day14;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/14">Disk Defragmentation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart1 extends DiskDefragmentation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskDefragmentationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of squares
     */
    @Override
    protected int solve(Set<Point> squares) {
        return squares.size();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DiskDefragmentationPart1 instance = new DiskDefragmentationPart1();

        String result = instance.solve("input-day14-2017.txt");

        LOGGER.info(result);
    }
}
