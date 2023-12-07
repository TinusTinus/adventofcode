package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/7">Camel Cards</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CamelCardsPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelCardsPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var sorted = lines.map(HandAndBid::parse)
                // Sort weak-to-strong
                .sorted()
                .toList();
        
        return IntStream.range(0, sorted.size())
                .map(i -> (i + 1) * sorted.get(i).bid())
                .sum();
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
 