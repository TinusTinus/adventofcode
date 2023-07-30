package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/16">Proboscidea Volcanium</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProboscideaVolcaniumPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var network = Network.parse(lines.toList());
        LOGGER.debug("{}", network);
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ProboscideaVolcaniumPart1();

        var result = instance.solve("input-day16-2022.txt");

        LOGGER.info(result);
    }
}
 