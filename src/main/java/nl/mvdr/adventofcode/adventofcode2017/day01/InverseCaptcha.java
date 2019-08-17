package nl.mvdr.adventofcode.adventofcode2017.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 1 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/1">Inverse Captcha</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class InverseCaptcha implements PathSolver<Integer> {

    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Integer> digits = Files.lines(inputFilePath)
                .findFirst()
                .get()
                .chars()
                .mapToObj(c -> "" + (char)c)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        int result = IntStream.range(0, digits.size())
                .filter(i -> digits.get(i).equals(digits.get(compareToIndex(i, digits.size()))))
                .map(digits::get)
                .sum();
        
        return Integer.valueOf(result);
    }

    /**
     * Determines which index to compare the digit at the current index to.
     * 
     * @param index current index
     * @param numberOfDigits total number of digits
     * @return index to compare to
     */
    abstract int compareToIndex(int index, int numberOfDigits);
}
