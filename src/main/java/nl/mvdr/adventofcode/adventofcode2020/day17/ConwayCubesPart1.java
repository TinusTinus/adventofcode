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
public class ConwayCubesPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConwayCubesPart1.class);

    private final int cycles;
    
    /**
     * Constructor.
     * 
     * @param cycles number of cycles to simulate
     */
    ConwayCubesPart1(int cycles) {
        this.cycles = cycles;
    }
    
    /** Constructor. */
    public ConwayCubesPart1() {
        this(6);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return number of active cubes after six cycles
     */
    @Override
    public int solve(Stream<String> lines) {
        PocketDimension pocketDimension = PocketDimension.parseInitialState(lines);
        pocketDimension = pocketDimension.simulateCycles(cycles);
        return pocketDimension.activeCubes().size();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ConwayCubesPart1 instance = new ConwayCubesPart1();

        String result = instance.solve("input-day17-2020.txt");

        LOGGER.info(result);
    }
}
 