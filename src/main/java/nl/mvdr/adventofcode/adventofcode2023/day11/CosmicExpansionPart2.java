package nl.mvdr.adventofcode.adventofcode2023.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/11">Cosmic Expansion</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CosmicExpansionPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CosmicExpansionPart2.class);

    private final int expansionFactor;
    
    /**
     * Constructor.
     */
    public CosmicExpansionPart2() {
        this(1_000_000);
    }
    
    /**
     * Constructor.
     * 
     * @param expansionFactor the factor by which the universe should be expanded;
     *       that is, the number of rows / columns by which to replace each empty row and column
     */
    CosmicExpansionPart2(int expansionFactor) {
        super();
        this.expansionFactor = expansionFactor;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var universe = Universe.parse(lines.toList());
        universe = universe.expand(expansionFactor);
        return universe.sumShortestPaths();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CosmicExpansionPart1();

        var result = instance.solve("input-day11-2023.txt");

        LOGGER.info(result);
    }
}
 