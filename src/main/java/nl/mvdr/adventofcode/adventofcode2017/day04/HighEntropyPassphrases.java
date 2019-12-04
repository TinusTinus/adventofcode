package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 4 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class HighEntropyPassphrases implements LongSolver {

    /**
     * {@inheritDoc}
     * 
     * @return number of valid passphrases
     */
    @Override
    public long solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isBlank))
                .map(line -> line.split("\\s"))
                .map(Arrays::asList)
                .filter(this::isValid)
                .count();
    }

    /**
     * Checks whether the given passphrase is valid.
     * 
     * @param passphrase passphrase, as a list of words
     * @return whether the passphrase is valid
     */
    abstract boolean isValid(List<String> passphrase);
}
