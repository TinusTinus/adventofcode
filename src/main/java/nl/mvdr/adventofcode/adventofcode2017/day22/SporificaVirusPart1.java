package nl.mvdr.adventofcode.adventofcode2017.day22;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 22 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/22">Sporifica Virus</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SporificaVirusPart1 implements PathSolver<Integer> {

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
    public Integer solve(Path inputFilePath) throws IOException {
        Grid grid = Grid.parse(inputFilePath);
        
        grid.burst(bursts, false);
        
        return Integer.valueOf(grid.getInfectionCount());
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
