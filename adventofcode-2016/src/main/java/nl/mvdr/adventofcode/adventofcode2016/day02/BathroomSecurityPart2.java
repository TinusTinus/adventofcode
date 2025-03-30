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
public class BathroomSecurityPart2 extends BathroomSecurity {

    private static final Logger LOGGER = LoggerFactory.getLogger(BathroomSecurityPart2.class);
    
    public BathroomSecurityPart2() {
        // Keypad:
        //     1
        //   2 3 4
        // 5 6 7 8 9
        //   A B C
        //     D
        super(Map.ofEntries(
                Map.entry(new Point(2, 0), "1"),
                Map.entry(new Point(1, 1), "2"),
                Map.entry(new Point(2, 1), "3"),
                Map.entry(new Point(3, 1), "4"),
                Map.entry(new Point(0, 2), "5"),
                Map.entry(new Point(1, 2), "6"),
                Map.entry(new Point(2, 2), "7"),
                Map.entry(new Point(3, 2), "8"),
                Map.entry(new Point(4, 2), "9"),
                Map.entry(new Point(1, 3), "A"),
                Map.entry(new Point(2, 3), "B"),
                Map.entry(new Point(3, 3), "C"),
                Map.entry(new Point(2, 4), "D")
            ));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BathroomSecurityPart2 instance = new BathroomSecurityPart2();

        String result = instance.solve("input-day02.txt");

        LOGGER.info(result);
    }
}
