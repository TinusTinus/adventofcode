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
