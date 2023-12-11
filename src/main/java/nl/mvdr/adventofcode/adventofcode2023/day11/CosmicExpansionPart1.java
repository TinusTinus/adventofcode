package nl.mvdr.adventofcode.adventofcode2023.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/11">Cosmic Expansion</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CosmicExpansionPart1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CosmicExpansionPart1.class);

    @Override
    public String solve(String inputfile) {
        // Part 1 is essentially just a special case of part 2.
        return new CosmicExpansionPart2(2).solve(inputfile);
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
 