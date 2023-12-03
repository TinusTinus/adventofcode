package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/03">Gear Ratios</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GearRatiosPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GearRatiosPart1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        var lines = linesStream.toList();
        
        var result = 0;
        
        for (var lineNumber = 0; lineNumber != lines.size(); lineNumber++) {
            var line = lines.get(lineNumber);
            var i = 0;
            while (i < line.length()) {
                // Search for the start of the next number on this line.
                while (i < line.length() && !Character.isDigit(line.charAt(i))) {
                    i++;
                }
                if (i < line.length()) {
                    // Found the start of a new number.
                    var numberString = "";
                    while (i < line.length() && Character.isDigit(line.charAt(i))) {
                        numberString = numberString + line.charAt(i);
                        i++;
                    }
                    // Found the complete number. Check whether it is a part number.
                    if (isPartNumber(lines, lineNumber, numberString, i - numberString.length())) {
                        var number = Integer.parseInt(numberString);
                        result = result + number;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Checks whether the given number is a part number.
     * 
     * That is, checks whether the number is adjacent to a symbol, other than a digit or '.'.
     * 
     * @param lines puzzle input
     * @param lineNumber the line number containing the number
     * @param numberString string representation of the number
     * @param indexOfNumber index of the start of the number
     * @return whether the given number is a part number
     */
    private static boolean isPartNumber(List<String> lines, int lineNumber, String numberString, int indexOfNumber) {
        return IntStream.range(lineNumber - 1, lineNumber + 2)
            .filter(y -> 0 <= y)
            .filter(y -> y < lines.size())
            .mapToObj(lines::get)
            .anyMatch(line -> 
                IntStream.range(indexOfNumber - 1, indexOfNumber + numberString.length() + 2)
                    .filter(x -> 0 <= x)
                    .filter(x -> x < line.length())
                    .map(line::charAt)
                    .anyMatch(c -> !Character.isDigit((char)c) && c != '.')
                );
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new GearRatiosPart1();

        var result = instance.solve("input-day03-2023.txt");

        LOGGER.info(result);
    }
}
 