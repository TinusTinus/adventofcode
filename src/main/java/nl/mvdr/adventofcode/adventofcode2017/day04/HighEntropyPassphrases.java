package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 4 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class HighEntropyPassphrases implements PathSolver<Long> {

    /**
     * {@inheritDoc}
     * 
     * @return number of valid passphrases
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        long result = Files.lines(inputFilePath)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(line -> line.split("\\s"))
                .map(Arrays::asList)
                .filter(this::isValid)
                .count();
        
        return Long.valueOf(result);
    }

    /**
     * Checks whether the given passphrase is valid.
     * 
     * @param passphrase passphrase, as a list of words
     * @return whether the passphrase is valid
     */
    abstract boolean isValid(List<String> passphrase);
}
