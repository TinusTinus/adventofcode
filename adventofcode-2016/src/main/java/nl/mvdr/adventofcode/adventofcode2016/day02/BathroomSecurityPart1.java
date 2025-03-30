package nl.mvdr.adventofcode.adventofcode2016.day02;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 2 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/2">Bathroom Security</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BathroomSecurityPart1 extends BathroomSecurity {

    private static final Logger LOGGER = LoggerFactory.getLogger(BathroomSecurityPart1.class);
    
    /** Constructor. */
    public BathroomSecurityPart1() {
        // Keypad:
        //   1 2 3
        //   4 5 6
        //   7 8 9
        super(Map.of(
                new Point(0, 0), "1",
                new Point(1, 0), "2",
                new Point(2, 0), "3",
                new Point(0, 1), "4",
                new Point(1, 1), "5",
                new Point(2, 1), "6",
                new Point(0, 2), "7",
                new Point(1, 2), "8",
                new Point(2, 2), "9"
            ));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BathroomSecurityPart1 instance = new BathroomSecurityPart1();

        String result = instance.solve("input-day02.txt");

        LOGGER.info(result);
    }
}
