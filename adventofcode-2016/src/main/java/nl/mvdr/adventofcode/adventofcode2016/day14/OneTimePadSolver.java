package nl.mvdr.adventofcode.adventofcode2016.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

class OneTimePadSolver implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneTimePadSolver.class);

    private final int hashCount;
    private final Map<String, String> hashCache;
    
    /// Constructor.
    ///
    /// @param hashCount the number of times to apply the hash function to each input
    OneTimePadSolver(int hashCount) {
        this.hashCount = hashCount;
        this.hashCache = new HashMap<>();
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var salt = lines.findFirst().orElseThrow();
        
        return IntStream.iterate(0, index -> index + 1)
                .filter(index -> isKey(salt, index))
                .skip(63)
                .findFirst()
                .orElseThrow();
    }

    // Default visibility for unit test
    boolean isKey(String salt, int index) {
        var tripletCharacter = findTriplet(salt, index);
        boolean result = tripletCharacter != null && IntStream.range(index + 1, index + 1001)
                .anyMatch(i -> containsQuintuplet(salt, i, tripletCharacter.charValue()));
        if (result) {
            LOGGER.debug("Key found at index {}", Integer.valueOf(index));
        }
        return result;
    }
    
    private String hash(String salt, int index) {
        return hashCache.computeIfAbsent(salt + index, this::computeHash);
    }

    private String computeHash(String input) {
        String result = input;
        for (int i = 0; i != hashCount; i++) {
            result = DigestUtils.md5Hex(result);
        }
        return result;
    }
    
    // Default visibility for unit test
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

    private boolean containsQuintuplet(String salt, int index, char character) {
        var hash = hash(salt, index);
        var result =  hash.contains("" + character + character + character + character + character);
        if (result) {
            LOGGER.debug("{} quintuplet found in hash {} at index {}", Character.valueOf(character), hash, Integer.valueOf(index));
        }
        return result;
    }
}
