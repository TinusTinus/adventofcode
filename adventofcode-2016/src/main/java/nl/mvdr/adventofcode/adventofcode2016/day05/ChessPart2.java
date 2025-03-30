package nl.mvdr.adventofcode.adventofcode2016.day05;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 5 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/5">How About a Nice Game of Chess?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChessPart2 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return eight-character password for the second door
     */
    @Override
    public String solve(Stream<String> lines) {
        String doorId = lines.findFirst().orElseThrow();
        
        // map containing, for each index, the computed character in that position
        Map<Integer, Character> result = new HashMap<>();
        
        // consider all integers, starting from 0
        IntStream.range(0, Integer.MAX_VALUE)
            // append the number to the door id
            .mapToObj(i -> doorId + i)
            // compute md5 hash
            .map(DigestUtils::md5Hex)
            .peek(LOGGER::debug)
            // only consider values where the hash starts with five zeroes
            .filter(hash -> hash.startsWith("00000"))
            // only consider values where the sixth character is in position 0-7
            .filter(hash -> '0' <= hash.charAt(5))
            .filter(hash -> hash.charAt(5) < '8')
            // only consider the first result for each position
            .filter(hash -> !result.containsKey(Integer.valueOf("" + hash.charAt(5))))
            // limit the password to the first 8 resulting characters
            .limit(8)
            // insert into the result map
            .peek(hash -> result.put(Integer.valueOf("" + hash.charAt(5)), Character.valueOf(hash.charAt(6))))
            // log intermediate results for extra pride
            .forEach(hash -> LOGGER.info("{}: {}", hash, toString(result)));
        
        return toString(result);
    }
    
    private static String toString(Map<Integer, Character> partialPassword) {
        return IntStream.range(0, 8)
                .mapToObj(i -> partialPassword.getOrDefault(Integer.valueOf(i), Character.valueOf('_')))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChessPart2 instance = new ChessPart2();

        String result = instance.solve("input-day05.txt");

        LOGGER.info(result);
    }
}
