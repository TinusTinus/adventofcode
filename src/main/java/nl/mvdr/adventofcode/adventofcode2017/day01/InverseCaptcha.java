package nl.mvdr.adventofcode.adventofcode2017.day01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/1">Inverse Captcha</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class InverseCaptcha implements IntSolver {

    @Override
    public int solve(Stream<String> lines) {
        List<Integer> digits = lines.findFirst()
                .orElseThrow()
                .chars()
                .mapToObj(c -> "" + (char)c)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        return IntStream.range(0, digits.size())
                .filter(i -> digits.get(i).equals(digits.get(compareToIndex(i, digits.size()))))
                .map(digits::get)
                .sum();
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
