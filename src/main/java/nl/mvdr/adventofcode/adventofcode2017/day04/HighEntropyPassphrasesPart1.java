package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 4 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart1 extends HighEntropyPassphrases {

    private static final Logger LOGGER = LoggerFactory.getLogger(HighEntropyPassphrasesPart1.class);
    
    @Override
    protected boolean isValid(List<String> passphrase) {
        Set<String> distinctWords = new HashSet<>(passphrase);
        return passphrase.size() == distinctWords.size();
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
