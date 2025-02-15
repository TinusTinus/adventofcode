package nl.mvdr.adventofcode.adventofcode2022.day20;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An arrangement of numbers, representing a file.
 *
 * @author Martijn van de Rijdt
 */
record Arrangement(List<Number> numbers) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Arrangement.class);
    
    /**
     * Parses the puzzle input.
     * 
     * @param lines puzzle input
     * @param applyDecryptionKey whether the decryption key should be applied to each value
     * @return list of numbers
     */
    static Arrangement parse(Stream<String> lines, boolean applyDecryptionKey) {
        return new Arrangement(Number.parse(lines.toList(), applyDecryptionKey));
    }
    
    /**
     * Mixes this arrangement once.
     * 
     * @return mixed arrangement
     */
    Arrangement mix() {
        return mix(1);
    }
    
    /**
     * Mixes this arrangement the given number of times.
     * 
     * @param times the number of times to mix
     * @return mixed arrangement
     */
    Arrangement mix(int times) {
        List<Number> result = new LinkedList<>(numbers);
        for (int round = 0; round != times; round++) {
            for (Number number : numbers) {
                var index = result.indexOf(number);
                result.remove(index);
                
                var newIndex = Math.floorMod(index + number.value(), result.size());
                
                result.add(newIndex, number);
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Moved {} from {} to {}, result: {}", number, Integer.valueOf(index), Integer.valueOf(newIndex), result);
                }
            }
            
            LOGGER.debug("After {} round(s) of mixing: {}", Integer.valueOf(round), result);
        }
        
        return new Arrangement(result);
    }

    /**
     * Gets the grove coordinates.
     * 
     * @param mixed numbers after mixing
     * @return grove coordinates
     */
    long findGroveCoordinates() {
        var indexOf0 = IntStream.range(0, numbers.size())
                .filter(i -> numbers.get(i).value() == 0)
                .findFirst()
                .orElseThrow();
        return IntStream.of(1_000, 2_000, 3_000)
                .map(i -> indexOf0 + i)
                .map(i -> i % numbers.size())
                .mapToObj(numbers::get)
                .mapToLong(Number::value)
                .sum();
    }
    
    @Override
    public String toString() {
        return numbers.toString();
    }
}
