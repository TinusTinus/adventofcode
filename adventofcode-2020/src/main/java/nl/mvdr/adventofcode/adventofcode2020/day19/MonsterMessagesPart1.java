package nl.mvdr.adventofcode.adventofcode2020.day19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 18 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Monster Messages</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart1 extends MonsterMessages {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterMessagesPart1.class);

    /** Constructor. */
    public MonsterMessagesPart1() {
        super(false);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MonsterMessagesPart1 instance = new MonsterMessagesPart1();

        String result = instance.solve("input-day19-2020.txt");

        LOGGER.info(result);
    }
}
 