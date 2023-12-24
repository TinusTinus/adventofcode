package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/24">Never Tell Me The Odds</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeverTellMeTheOddsPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var hailstones = lines.map(Hailstone::parse)
                .toList();
        
        LOGGER.info("hailstones: {}", hailstones);
        
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NeverTellMeTheOddsPart1();

        var result = instance.solve("input-day24-2023.txt");

        LOGGER.info(result);
    }
}
 