package nl.mvdr.adventofcode.adventofcode2020.day04;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to the day 4 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/4">Passport Processing</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PassportProcessingPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassportProcessingPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of valid passports, ignoring the country id property
     */
    @Override
    public long solve(Stream<String> lines) {
        return Passport.parse(lines)
                .stream()
                .filter(Passport::hasRequiredValues)
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PassportProcessingPart1 instance = new PassportProcessingPart1();

        String result = instance.solve("input-day04-2020.txt");

        LOGGER.info(result);
    }
}
 