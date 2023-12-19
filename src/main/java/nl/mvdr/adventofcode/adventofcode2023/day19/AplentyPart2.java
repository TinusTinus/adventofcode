package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/19">Aplenty</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AplentyPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AplentyPart2.class);

    @Override
    public long solve(Stream<String> linesStream) {
        List<String> lines = linesStream.takeWhile(Predicate.not(String::isEmpty))
                .toList();
        var workflows = Workflow.parse(lines);
        
        return 0L; // TODO implement
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new AplentyPart2();
        
        var result = instance.solve("input-day19-2023.txt");

        LOGGER.info(result);
    }
}
 