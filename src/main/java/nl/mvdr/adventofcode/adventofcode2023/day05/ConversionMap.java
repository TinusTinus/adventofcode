package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A map which describes how to convert numbers from a source category into numbers in a destination category.
 *
 * @author Martijn van de Rijdt
 */
record ConversionMap(List<ConversionMapRange> ranges) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionMap.class);
    
    /**
     * Reads conversion maps from the puzzle input.
     * 
     * @param input entire puzzle input
     * @return conversion maps
     */
    static List<ConversionMap> parse(List<String> input) {
        List<ConversionMap> result = new ArrayList<>();
        
        // Skip the first line, containing seeds
        int index = 1;
        
        while (index != input.size()) {
            // Find the start of the next section
            while (!input.get(index).endsWith(":")) {
                index++;
            }
            LOGGER.debug("Reading {}", input.get(index));
            index++;
            
            List<ConversionMapRange> ranges = new ArrayList<>();
            while (index < input.size() && !input.get(index).isEmpty()) {
                var range = ConversionMapRange.parse(input.get(index));
                ranges.add(range);
                index++;
            }
            var map = new ConversionMap(ranges);
            result.add(map);
        }
        
        return result;
    }
    
    /**
     * Maps the given source number to a destination number.
     * 
     * @param sourceNumber source number (for example, if this is a seed-to-soil map, a seed number)
     * @return destination number (in the above example: a soil number)
     */
    long map(long sourceNumber) {
        return ranges.stream()
            .filter(range -> range.contains(sourceNumber))
            .mapToLong(range -> range.map(sourceNumber))
            .findFirst()
            .orElse(sourceNumber);
    }
}
