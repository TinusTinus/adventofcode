package nl.mvdr.adventofcode.adventofcode2022.day20;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/20">Grove Positioning System</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GrovePositioningSystemPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrovePositioningSystemPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        List<Number> encryptedFile = Number.parse(lines.toList());
        List<Number> mixed = mix(encryptedFile);
        return getGroveCoordinates(mixed);
    }

    /**
     * Mixes the given numbers.
     * 
     * @param encryptedFile numbers to mix
     * @return mixed numbers
     */
    private List<Number> mix(List<Number> encryptedFile) {
        LOGGER.debug("Initial arrangement: {}", encryptedFile);
        
        List<Number> result = new LinkedList<>(encryptedFile);
        for (Number number : encryptedFile) {
            var index = result.indexOf(number);
            result.remove(index);
            
            var newIndex = index + number.value();
            newIndex = newIndex % result.size();
            while (newIndex < 0) {
                newIndex = newIndex + result.size();
            }
            
            result.add(newIndex, number);
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Moved {} from {} to {}, result: {}", number, Integer.valueOf(index), Integer.valueOf(newIndex), result);
            }
        }
        
        LOGGER.debug("Mixed: {}", result);
        
        return result;
    }
    
    /**
     * Gets the grove coordinates.
     * 
     * @param mixed numbers after mixing
     * @return grove coordinates
     */
    private int getGroveCoordinates(List<Number> mixed) {
        var indexOf0 = IntStream.range(0, mixed.size())
                .filter(i -> mixed.get(i).value() == 0)
                .findFirst()
                .orElseThrow();
        return IntStream.of(1_000, 2_000, 3_000)
                .map(i -> indexOf0 + i)
                .map(i -> i % mixed.size())
                .mapToObj(mixed::get)
                .mapToInt(Number::value)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new GrovePositioningSystemPart1();

        var result = instance.solve("input-day20-2022.txt");

        LOGGER.info(result);
    }
}
 