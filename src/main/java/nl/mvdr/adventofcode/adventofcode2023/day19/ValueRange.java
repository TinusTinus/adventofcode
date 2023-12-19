package nl.mvdr.adventofcode.adventofcode2023.day19;

/**
 * A range of possible values for a {@link Property}.
 *
 * @author Martijn van de Rijdt
 */
record ValueRange(int startInclusive, int endExclusive) {
    
    private static final int MINIMUM_VALUE = 1;
    private static final int MAXIMUM_VALUE = 4000;

    /**
     * Range of all possible property values.
     */
    static final ValueRange FULL_RANGE = new ValueRange(MINIMUM_VALUE, MAXIMUM_VALUE + 1);
    
    /**
     * Constructor.
     * 
     * @param startInclusive start of the range
     * @param endExclusive end of the range
     */
    public ValueRange(int startInclusive, int endExclusive) {
        if (startInclusive < MINIMUM_VALUE) {
            throw new IllegalArgumentException("Invalid start of range: " + startInclusive);
        }
        if (MAXIMUM_VALUE + 1 < endExclusive) {
            throw new IllegalArgumentException("Invalid end of range: " + endExclusive);
        }
        if (endExclusive < startInclusive) {
            throw new IllegalArgumentException("Invalid range: " + startInclusive + ", " + endExclusive);
        }
        
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }
    
    /**
     * @return whether this range is empty
     */
    boolean isEmpty() {
        return startInclusive == endExclusive;
    }
    
    /**
     * @return number of values within this range
     */
    int size() {
        return endExclusive - startInclusive;
    }
}
