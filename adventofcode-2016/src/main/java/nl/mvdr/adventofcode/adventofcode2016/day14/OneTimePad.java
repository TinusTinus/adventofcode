package nl.mvdr.adventofcode.adventofcode2016.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

class OneTimePad implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneTimePad.class);

    private final int hashCount;
    
    private Map<String, String> hashCache = new HashMap<>();
    
    OneTimePad(int hashCount) {
        this.hashCount = hashCount;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var salt = lines.findFirst().orElseThrow();
        
        return IntStream.iterate(0, i -> i + 1)
                .filter(i -> isKey(salt, i))
                .peek(i -> LOGGER.debug("Key found at index {}", Integer.valueOf(i)))
                .skip(63)
                .findFirst()
                .orElseThrow();
    }

    boolean isKey(String salt, int index) {
        var tripletCharacter = findTriplet(salt, index);
        return tripletCharacter != null && IntStream.range(index + 1, index + 1001)
                .anyMatch(i -> containsQuintuplet(salt, i, tripletCharacter.charValue()));
    }
    
    String hash(String salt, int index) {
        String input = salt + index;
        
        String result = hashCache.get(input);
        
        if (result == null) {
            result = input;
            for (int i = 0; i != hashCount; i++) {
                result = DigestUtils.md5Hex(result);
            }
            hashCache.put(input, result);
        }
        return result;
    }
    
    Character findTriplet(String salt, int index) {
        var hash = hash(salt, index);
        var result = IntStream.range(0, hash.length() - 2)
                .filter((i -> hash.charAt(i) == hash.charAt(i + 1) && hash.charAt(i) == hash.charAt(i + 2)))
                .mapToObj(i -> Character.valueOf(hash.charAt(i)))
                .findFirst()
                .orElse(null);
        if (result != null) {
            LOGGER.debug("Triplet found in hash {} at index {}: {}", hash, Integer.valueOf(index), result);
        }
        return result;
    }

    boolean containsQuintuplet(String salt, int index, char character) {
        var hash = hash(salt, index);
        var result =  hash.contains("" + character + character + character + character + character);
        if (result) {
            LOGGER.debug("{} quintuplet found in hash {} at index {}", Character.valueOf(character), hash, Integer.valueOf(index));
        }
        return result;
    }
}
