package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/4">Scratchcards</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ScratchcardsPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScratchcardsPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        return lines.map(Scratchcard::parse)
                .mapToInt(Scratchcard::score)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ScratchcardsPart1();

        var result = instance.solve("input-day04-2023.txt");

        LOGGER.info(result);
    }
}
 