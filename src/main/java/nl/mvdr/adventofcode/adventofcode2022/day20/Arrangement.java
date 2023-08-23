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
     * @return list of numbers
     */
    static Arrangement parse(Stream<String> lines) {
        return new Arrangement(Number.parse(lines.toList()));
    }
    
    /**
     * Mixes this arrangement once.
     * 
     * @return mixed arrangement
     */
    Arrangement mix() {
        List<Number> result = new LinkedList<>(numbers);
        for (Number number : numbers) {
            var index = result.indexOf(number);
            result.remove(index);
            
            var newIndex = index + number.value();
            newIndex = newIndex % result.size();
            // TODO see if we can find a more efficient way to do the following
            while (newIndex < 0) {
                newIndex = newIndex + result.size();
            }
            
            result.add(newIndex, number);
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Moved {} from {} to {}, result: {}", number, Integer.valueOf(index), Integer.valueOf(newIndex), result);
            }
        }
        
        LOGGER.debug("Mixed: {}", result);
        
        return new Arrangement(result);
    }

    /**
     * Gets the grove coordinates.
     * 
     * @param mixed numbers after mixing
     * @return grove coordinates
     */
    int findGroveCoordinates() {
        var indexOf0 = IntStream.range(0, numbers.size())
                .filter(i -> numbers.get(i).value() == 0)
                .findFirst()
                .orElseThrow();
        return IntStream.of(1_000, 2_000, 3_000)
                .map(i -> indexOf0 + i)
                .map(i -> i % numbers.size())
                .mapToObj(numbers::get)
                .mapToInt(Number::value)
                .sum();
    }
    
    @Override
    public String toString() {
        return numbers.toString();
    }
}
