package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 10 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/10">Balance Bots</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BalanceBots implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceBots.class);
    
    private final int chip0;
    private final int chip1;
    
    /** Constructor. */
    public BalanceBots() {
        this(61, 17);
    }
    
    /**
     * Constructor.
     * 
     * @param chip0 one microchip to compare
     * @param chip1 other microchip to compare
     */
    BalanceBots(int chip0, int chip1) {
        super();
        this.chip0 = chip0;
        this.chip1 = chip1;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return number of the bot responsible for comparing value-{@code chip0} microchips with value-{@code chip1} microchips
     */
    @Override
    public int solve(Stream<String> lines) {
        return 0; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BalanceBots instance = new BalanceBots();

        String result = instance.solve("input-day10-2016.txt");

        LOGGER.info(result);
    }
}
