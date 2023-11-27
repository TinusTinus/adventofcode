package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/3">Binary Diagnostic</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryDiagnosticPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryDiagnosticPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var numbers = lines.toList();
        
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BinaryDiagnosticPart2();

        var result = instance.solve("input-day03-2021.txt");

        LOGGER.info(result);
    }
}
 