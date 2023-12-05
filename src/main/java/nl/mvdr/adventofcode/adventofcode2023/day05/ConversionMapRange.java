package nl.mvdr.adventofcode.adventofcode2023.day05;

/**
 * Range for mapping resources from a source category to a destination category.
 *
 * @author Martijn van de Rijdt
 */
record ConversionMapRange(Range destinationRange, Range sourceRange) {
    
    /**
     * Parses a line from the puzzle input.
     * 
     * @param text textual representation of a range, for example: "50 98 2"
     * @return range
     */
    static ConversionMapRange parse(String text) {
        var parts = text.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Unable to parse: " + text);
        }
        
        var destinationRangeStart = Long.parseLong(parts[0]);
        var sourceRangeStart = Long.parseLong(parts[1]);
        var rangeLength = Long.parseLong(parts[2]);
        
        var destinationRange = new Range(destinationRangeStart, rangeLength);
        var sourceRange = new Range(sourceRangeStart, rangeLength);
        return new ConversionMapRange(destinationRange, sourceRange);
    }
    
    /**
     * Checks whether the given source number falls within this range.
     * 
     * @param sourceNumber source number (for example, if this is part of a seed-to-soil map, a seed number)
     * @return whether the given number is in this range
     */
    boolean contains(long sourceNumber) {
        return sourceRange.contains(sourceNumber);
    }
    
    /**
     * Maps the given source number to a destination number.
     * 
     * @param sourceNumber source number (for example, if this is part of a seed-to-soil map, a seed number); must be in this range
     * @return destination number (in the above example: a soil number)
     */
    long map(long sourceNumber) {
        var range = new Range(sourceNumber, 1);
        var mapped = map(range);
        if (mapped.length() != 1) {
            throw new IllegalStateException("Failed to map source number " + sourceNumber + ", resulting incorrect range: " + mapped);
        }
        return mapped.start();
    }
    
    /**
     * Maps the given source number range to a destination range.
     * 
     * @param range source range to be mapped; must be contained entirely within this range
     * @return destination range
     */
    Range map(Range range) {
        if (!sourceRange.contains(range)) {
            throw new IllegalArgumentException("Range " + range + " is not contained within " + this);
        }
        var offset = Math.subtractExact(range.start(), sourceRange.start());
        var destinationStart = Math.addExact(destinationRange.start(), offset);
        return new Range(destinationStart, range.length());
    }
    
    @Override
    public String toString() {
        return destinationRange.start() + " " + sourceRange.start() + " " + sourceRange.length();
    }
}
