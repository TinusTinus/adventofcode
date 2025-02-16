package nl.mvdr.adventofcode.adventofcode2023.day07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/7">Camel Cards</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CamelCardsPart1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelCardsPart1.class);

    @Override
    public String solve(String inputfile) {
        return new CamelCards<>(Part1Card.class).solve(inputfile);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CamelCardsPart1();

        var result = instance.solve("input-day07-2023.txt");

        LOGGER.info(result);
    }
}
 