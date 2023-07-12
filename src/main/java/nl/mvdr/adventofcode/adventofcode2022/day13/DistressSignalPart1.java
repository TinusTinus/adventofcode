package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/13">Distress Signal</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DistressSignalPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistressSignalPart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var pairs = PacketPair.parse(lines.toList());
        return IntStream.range(0, pairs.size())
                .filter(i -> pairs.get(i).isInCorrectOrder())
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new DistressSignalPart1();

        var result = instance.solve("input-day13-2022.txt");

        LOGGER.info(result);
    }
}
 