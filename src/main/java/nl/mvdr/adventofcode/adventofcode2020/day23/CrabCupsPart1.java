package nl.mvdr.adventofcode.adventofcode2020.day23;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to the day 23 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/23">Crab Cups</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCupsPart1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrabCupsPart1.class);

    private final int turns;
    
    /**
     * Constructor.
     * 
     * @param turns the number of turns to perform
     */
    CrabCupsPart1(int turns) {
        super();
        this.turns = turns;
    }
    
    /** Constructor. */
    public CrabCupsPart1() {
        this(100);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return order of cups, starting clockwise of the cup labeled 1
     */
    @Override
    public String solve(Stream<String> lines) {
        return GameState.parse(lines)
                .perform(turns)
                .getOrder();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CrabCupsPart1 instance = new CrabCupsPart1();

        String result = instance.solve("input-day23-2020.txt");

        LOGGER.info(result);
    }
}
 