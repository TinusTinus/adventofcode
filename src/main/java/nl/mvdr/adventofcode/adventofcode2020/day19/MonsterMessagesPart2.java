package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 18 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Monster Messages</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart2 extends MonsterMessages {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterMessagesPart2.class);

    /** Constructor. */
    public MonsterMessagesPart2() {
        super(true);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MonsterMessagesPart2 instance = new MonsterMessagesPart2();

        String result = instance.solve("input-day19-2020.txt");

        LOGGER.info(result);
    }
}
 