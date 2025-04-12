package nl.mvdr.adventofcode.adventofcode2016.day14;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        var salt = lines.findFirst().orElseThrow();
        
        return IntStream.iterate(0, i -> i + 1)
                .filter(i -> isKey(salt, i))
                .peek(i -> LOGGER.debug("Key found at index {}", Integer.valueOf(i)))
                .skip(63)
                .findFirst()
                .orElseThrow(); // The accepted answer is the 61st key for my input. For the example it's the 63rd key. Why? There must be keys this solution is not detecting.
    }

    static boolean isKey(String salt, int index) {
        var tripletCharacter = findTriplet(salt, index);
        return tripletCharacter != null && IntStream.range(index + 1, index + 1001)
                .anyMatch(i -> containsQuintuplet(salt, i, tripletCharacter.charValue()));
    }
    
    static String hash(String salt, int index) {
        return DigestUtils.md5Hex(salt + index);
    }
    
    static Character findTriplet(String salt, int index) {
        var hash = hash(salt, index);
        var result = IntStream.range(0, hash.length() - 3)
                .filter((i -> hash.charAt(i) == hash.charAt(i + 1) && hash.charAt(i) == hash.charAt(i + 2)))
                .mapToObj(i -> Character.valueOf(hash.charAt(i)))
                .findFirst()
                .orElse(null);
        if (result != null) {
            LOGGER.debug("Triplet found in hash {} at index {}: {}", hash, Integer.valueOf(index), result);
        }
        return result;
    }

    static boolean containsQuintuplet(String salt, int index, char character) {
        var hash = hash(salt, index);
        var result =  hash.contains("" + character + character + character + character + character);
        if (result) {
            LOGGER.debug("{} quintuplet found in hash {} at index {}", Character.valueOf(character), hash, Integer.valueOf(index));
        }
        return result;
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day14.txt");

        LOGGER.info(result);
    }
}
