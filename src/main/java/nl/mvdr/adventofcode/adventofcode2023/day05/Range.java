package nl.mvdr.adventofcode.adventofcode2023.day05;

/**
 * A range of numbers.
 *
 * @author Martijn van de Rijdt
 */
record Range(long start, long length) {

    /**
     * Checks whether the given number falls within this range.
     * 
     * @param number the number to check
     * @return whether the given number is in this range
     */
    boolean contains(long number) {
        return start <= number && number < start + length;
    }
    
    /**
     * Checks whether the entirety of the given other range is contained within this one.
     * 
     * @param other other range
     * @return whether the given other range is entirely contained within this one
     */
    boolean contains(Range other) {
        return start <= other.start() && other.start() + other.length() <= start + length;
    }
}
