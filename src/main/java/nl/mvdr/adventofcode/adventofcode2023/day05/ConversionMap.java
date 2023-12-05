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
record ConversionMap(List<ConversionMapRange> conversionRanges) {
    
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
        Range range = new Range(sourceNumber, 1);
        var ranges = List.of(range);
        var mappedRanges = map(ranges);
        if (mappedRanges.size() != 1) {
            throw new IllegalStateException();
        }
        var mappedRange = mappedRanges.getFirst();
        if (mappedRange.length() != 1) {
            throw new IllegalStateException();
        }
        return mappedRange.start();
    }
    
    /**
     * Maps the given list of source ranges to a list of destination ranges.
     * 
     * @param ranges source ranges
     * @return destination ranges
     */
    List<Range> map(List<Range> ranges) {
        List<Range> remainingSourceRanges = new ArrayList<>(ranges);
        List<Range> destinationRanges = new ArrayList<>();
        
        for (ConversionMapRange conversionRange : conversionRanges) {
            List<Range> newRemainingSourceRanges = new ArrayList<>();
            while (!remainingSourceRanges.isEmpty()) {
                var sourceRange = remainingSourceRanges.removeFirst();
                var overlap = conversionRange.sourceRange().overlappingPart(sourceRange);
                destinationRanges.add(conversionRange.map(overlap));
                newRemainingSourceRanges.add(conversionRange.sourceRange().precedingPart(sourceRange));
                newRemainingSourceRanges.add(conversionRange.sourceRange().followingPart(sourceRange));
            }
            remainingSourceRanges = new ArrayList<>(Range.removeEmptyRanges(newRemainingSourceRanges));
        }
        destinationRanges.addAll(remainingSourceRanges);
        return Range.removeEmptyRanges(destinationRanges);
    }
}
