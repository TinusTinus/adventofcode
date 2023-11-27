package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/3">Binary Diagnostic</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryDiagnosticPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryDiagnosticPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var numbers = lines.toList();
        
        var gammaRateString = calculateGammaRate(numbers);
        var gammaRate = Integer.parseInt(gammaRateString, 2);
        LOGGER.debug("Gamma rate: {}, decimal: {}", gammaRateString, Integer.valueOf(gammaRate));
        
        var epsilonRateString = BinaryDiagnosticHelper.invert(gammaRateString);
        int epsilonRate = Integer.parseInt(epsilonRateString, 2);
        LOGGER.debug("Epsilon rate: {}, decimal: {}", epsilonRateString, Integer.valueOf(epsilonRate));
        
        return gammaRate * epsilonRate;
    }
    
    /**
     * Calculates the gamma rate.
     * 
     * @param numbers diagnostic report 
     * @return gamma rate
     */
    private static String calculateGammaRate(List<String> numbers) {
        return IntStream.range(0, numbers.get(0).length())
                .mapToObj(i -> Character.valueOf(BinaryDiagnosticHelper.mostCommonCharacterAt(numbers, i)))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BinaryDiagnosticPart1();

        var result = instance.solve("input-day03-2021.txt");

        LOGGER.info(result);
    }
}
 