package nl.mvdr.adventofcode.adventofcode2017.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 22 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/22">Sporifica Virus</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SporificaVirusPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SporificaVirusPart1.class);

    private final int bursts;
    
    /** Constructor. */
    public SporificaVirusPart1() {
        this(10_000);
    }
    
    /**
     * Constructor.
     * 
     * @param bursts number of bursts to perform
     */
    SporificaVirusPart1(int bursts) {
        super();
        this.bursts = bursts;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return how many bursts cause a node to become infected
     */
    @Override
    public int solve(Stream<String> lines) {
        Grid grid = Grid.parse(lines);
        
        grid.burst(bursts, false);
        
        return grid.getInfectionCount();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SporificaVirusPart1 instance = new SporificaVirusPart1();

        String result = instance.solve("input-day22-2017.txt");

        LOGGER.info(result);
    }
}
