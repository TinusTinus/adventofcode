package nl.mvdr.adventofcode.adventofcode2016.day03;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 3 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/3">Squares With Three Sides</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SquaresPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of possible triangles
     */
    @Override
    public long solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Triangle::parse)
                .filter(Triangle::isPossible)
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SquaresPart1 instance = new SquaresPart1();

        String result = instance.solve("input-day03-2016.txt");

        LOGGER.info(result);
    }
}
