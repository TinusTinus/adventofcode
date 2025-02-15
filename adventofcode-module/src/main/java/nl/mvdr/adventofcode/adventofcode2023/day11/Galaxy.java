package nl.mvdr.adventofcode.adventofcode2023.day11;

/**
 * A galaxy
 * 
 * Note: in this puzzle we are not using {@link nl.mvdr.adventofcode.point.Point}.
 * {@code Point} uses int values for each coordinate.
 * However, part 2 of this puzzle requires the use of values larger than what fits in an int.
 *
 * @author Martijn van de Rijdt
 */
record Galaxy(long x, long y) {
    // No additional methods needed
}
