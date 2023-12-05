package nl.mvdr.adventofcode.adventofcode2023.day05;

/**
 * Range for mapping resources from a source category to a destination category.
 *
 * @author Martijn van de Rijdt
 */
record ConversionMapRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
    
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
        return new ConversionMapRange(destinationRangeStart, sourceRangeStart, rangeLength);
    }
    
    /**
     * Checks whether the given source number falls within this range.
     * 
     * @param sourceNumber source number (for example, if this is part of a seed-to-soil map, a seed number)
     * @return whether the given number is in this range
     */
    boolean contains(long sourceNumber) {
        return sourceRangeStart <= sourceNumber && sourceNumber < sourceRangeStart + rangeLength;
    }
    
    /**
     * Maps the given source number to a destination number.
     * 
     * @param sourceNumber source number (for example, if this is part of a seed-to-soil map, a seed number); must be in this range
     * @return destination number (in the above example: a soil number)
     */
    long map(long sourceNumber) {
        if (!contains(sourceNumber)) {
            throw new IllegalArgumentException("Source number " + sourceNumber + " not in range " + this);
        }
        var offset = sourceNumber - sourceRangeStart;
        return destinationRangeStart + offset;
    }
    
    @Override
    public String toString() {
        return destinationRangeStart + " " + sourceRangeStart + " " + rangeLength;
    }
}
