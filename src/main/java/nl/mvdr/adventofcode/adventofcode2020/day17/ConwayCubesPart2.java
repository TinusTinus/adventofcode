package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 17 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/17">Conway Cubes</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ConwayCubesPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConwayCubesPart2.class);

    private final int cycles;
    
    /**
     * Constructor.
     * 
     * @param cycles number of cycles to simulate
     */
    ConwayCubesPart2(int cycles) {
        this.cycles = cycles;
    }
    
    /** Constructor. */
    public ConwayCubesPart2() {
        this(6);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return number of active cubes after six cycles
     */
    @Override
    public int solve(Stream<String> lines) {
        return PocketDimension.parseInitialState4D(lines)
                .simulateCycles(cycles)
                .activeCubes()
                .size();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ConwayCubesPart2 instance = new ConwayCubesPart2();

        String result = instance.solve("input-day17-2020.txt");

        LOGGER.info(result);
    }
}
 