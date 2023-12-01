package nl.mvdr.adventofcode.adventofcode2023.day01;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2023's Advent of Code:
 * <a href="https://adventofcode.com/2023/day/1">Trebuchet?!</a>.
 *
 * @author Martijn van de Rijdt
 */
class Trebuchet implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trebuchet.class);

    private final Map<String, Integer> digits;
    
    /**
     * Constructor.
     * 
     * @param includeSpelledDigits whether to count spelled-out digits (such as "one" and "two")
     */
    @SuppressWarnings("boxing")
    Trebuchet(boolean includeSpelledDigits) {
        this.digits = new HashMap<>();
        digits.put("0", 0);
        digits.put("1", 1);
        digits.put("2", 2);
        digits.put("3", 3);
        digits.put("4", 4);
        digits.put("5", 5);
        digits.put("6", 6);
        digits.put("7", 7);
        digits.put("8", 8);
        digits.put("9", 9);
        if (includeSpelledDigits) {
            digits.put("one", 1);
            digits.put("two", 2);
            digits.put("three", 3);
            digits.put("four", 4);
            digits.put("five", 5);
            digits.put("six", 6);
            digits.put("seven", 7);
            digits.put("eight", 8);
            digits.put("nine", 9);
        }
    }
    
    @Override
    public int solve(Stream<String> lines) {
        return lines.mapToInt(this::findCalibrationValue)
            .sum();
    }
    
    /**
     * Finds a calibration value.
     * 
     * @param line line from the input, for example: "pqr3stu8vwx"
     * @return the calibration value; in this example: 38
     */
    private int findCalibrationValue(String line) {
        var firstIndexOf = digits.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), line::indexOf));
        var firstDigit = firstIndexOf.entrySet()
                .stream()
                .filter(entry -> 0 <= entry.getValue().intValue())
                .min(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey)
                .map(digits::get)
                .orElseThrow()
                .intValue();
        var lastIndexOf = digits.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), line::lastIndexOf));
        var lastDigit = lastIndexOf.entrySet()
                .stream()
                .max(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey)
                .map(digits::get)
                .orElseThrow()
                .intValue();
        
        LOGGER.debug("Line: {}, result: {}{}", line, Integer.valueOf(firstDigit), Integer.valueOf(lastDigit));
        
        return Integer.parseInt(firstDigit + "" + lastDigit);
    }
}
 