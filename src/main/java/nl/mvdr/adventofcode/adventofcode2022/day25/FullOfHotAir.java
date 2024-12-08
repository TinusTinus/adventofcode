package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/25">Full of Hot Air</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FullOfHotAir implements LinesSolver<SnafuNumber> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FullOfHotAir.class);

    @Override
    public SnafuNumber solve(Stream<String> lines) {
        return lines.map(SnafuNumber::parse)
                .reduce(SnafuNumber.ZERO, SnafuNumber::add);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new FullOfHotAir();

        var result = instance.solve("input-day25-2022.txt");

        LOGGER.info(result);
    }
}
 