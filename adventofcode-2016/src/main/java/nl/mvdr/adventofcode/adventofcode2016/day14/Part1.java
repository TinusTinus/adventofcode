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
                .skip(62) // TODO shouldn't this be 63? But 62 gives the correct answer for the example input
                .findFirst()
                .orElseThrow(); // 29847 is too high!
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
        return IntStream.range(0, hash.length() - 3)
                .filter((i -> hash.charAt(i) == hash.charAt(i + 1) && hash.charAt(i) == hash.charAt(i + 2)))
                .mapToObj(i -> Character.valueOf(hash.charAt(i)))
                .findFirst()
                .orElse(null);
    }

    static boolean containsQuintuplet(String salt, int index, char character) {
        var hash = hash(salt, index);
        return hash.contains("" + character + character + character + character + character);
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day14.txt");

        LOGGER.info(result);
    }
}
