package nl.mvdr.adventofcode.adventofcode2016.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 3 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/3">Squares With Three Sides</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart1 implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SquaresPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of possible triangles
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        long count = Files.lines(inputFilePath)
                .filter(Predicate.not(String::isEmpty))
                .map(Triangle::parse)
                .filter(Triangle::isPossible)
                .count();
        
        return Long.valueOf(count);
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
