package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 24 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/24">Planet of Discord</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PlanetOfDiscordPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetOfDiscordPart2.class);

    private final int minutes;
    
    /** Constructor. */
    public PlanetOfDiscordPart2() {
        this(200);
    }
    
    /**
     * Constructor.
     * 
     * @param minutes number of minutes
     */
    PlanetOfDiscordPart2(int minutes) {
        super();
        this.minutes = minutes;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return how many bugs are present after the specified number of minutes 
     */
    @Override
    public int solve(Stream<String> lines) {
        Layout layout = Layout.parse(true, lines);
        
        for (int i = 0; i != minutes; i++) {
            layout = layout.tick();
        }
        
        return layout.totalNumberOfBugs();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PlanetOfDiscordPart2 instance = new PlanetOfDiscordPart2();

        String result = instance.solve("input-day24-2019.txt");

        LOGGER.info(result);
    }
}
 