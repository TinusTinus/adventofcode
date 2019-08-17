package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 4 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart2 extends HighEntropyPassphrases {

    private static final Logger LOGGER = LoggerFactory.getLogger(HighEntropyPassphrasesPart2.class);
    
    @Override
    protected boolean isValid(List<String> passphrase) {
        Set<String> distinctWords = passphrase.stream()
                .map(this::alphabeticalOrder)
                .collect(Collectors.toSet());
        
        return passphrase.size() == distinctWords.size();
    }
    
    /**
     * Places the letters of the given word in alphabetical order.
     * 
     * @param word word
     * @return letters of the given word, in alphabetical order
     */
    private String alphabeticalOrder(String word) {
        return word.chars()
                .sorted()
                .mapToObj(c -> "" + (char)c)
                .collect(Collectors.joining());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HighEntropyPassphrasesPart2 instance = new HighEntropyPassphrasesPart2();

        String result = instance.solve("input-day04-2017.txt");

        LOGGER.info(result);
    }
}
