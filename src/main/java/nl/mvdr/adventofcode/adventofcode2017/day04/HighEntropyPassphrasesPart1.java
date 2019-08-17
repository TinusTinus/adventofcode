package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 4 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart1 implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HighEntropyPassphrasesPart1.class);
    
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
                .filter(passphrase -> passphrase.size() == new HashSet<String>(passphrase).size())
                .count();
        
        return Long.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HighEntropyPassphrasesPart1 instance = new HighEntropyPassphrasesPart1();

        String result = instance.solve("input-day04-2017.txt");

        LOGGER.info(result);
    }
}
