package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 12 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/12">The N-Body Problem</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NBodyProblemPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of simulation steps
     */
    @Override
    public long solve(Stream<String> lines) {
        List<Moon> system = lines.filter(Predicate.not(String::isBlank))
                .map(Moon::parse)
                .collect(Collectors.toList());
        LOGGER.debug("System: {}", system);
        
        return 0L; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        NBodyProblemPart2 instance = new NBodyProblemPart2();

        String result = instance.solve("input-day12-2019.txt");

        LOGGER.info(result);
    }
}
