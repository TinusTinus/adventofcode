package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 21 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/17">Allergen Assessment</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AllergenAssessmentPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllergenAssessmentPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return how many times ingredients appear, which definitely cannot contain any allergen
     */
    @Override
    public int solve(Stream<String> lines) {
        return 0; // TODO implement
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AllergenAssessmentPart1 instance = new AllergenAssessmentPart1();

        String result = instance.solve("input-day20-2020.txt");

        LOGGER.info(result);
    }
}
 