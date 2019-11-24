package nl.mvdr.adventofcode.adventofcode2016.day05;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to the day 5 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/5">How About a Nice Game of Chess?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChessPart1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return eight-character password
     */
    @Override
    public String solve(Stream<String> lines) {
        String doorId = lines.findFirst().orElseThrow();
        
        // consider all integers, starting from 0
        return IntStream.range(0, Integer.MAX_VALUE)
            // append the number to the door id
            .mapToObj(i -> doorId + i)
            // compute md5 hash
            .map(DigestUtils::md5Hex)
            .peek(LOGGER::debug)
            // only consider values where the hash starts with five zeroes
            .filter(hash -> hash.startsWith("00000"))
            // take the sixth character
            .map(hash -> Character.valueOf(hash.charAt(5)))
            .map(Object::toString)
            // limit the password to the first 8 resulting characters
            .limit(8)
            .collect(Collectors.joining());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChessPart1 instance = new ChessPart1();

        String result = instance.solve("input-day05-2016.txt");

        LOGGER.info(result);
    }
}
